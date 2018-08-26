package com.xing.apidemo.download;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author: chuanxing
 * Date: 18-8-9
 * Function:
 */
public class MultiThreadDownloadUtil {

    private static final String TAG = MultiThreadDownloadUtil.class.getSimpleName();

    public static void download(String path, Context context) {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5 * 1000);
            httpURLConnection.setConnectTimeout(5 * 1000);
            httpURLConnection.setRequestMethod("GET");

            int fileLength = httpURLConnection.getContentLength();
            Log.i(TAG, "fileLength: " + fileLength);
            String fileName = "downloadFile";
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "rwd");
            randomAccessFile.setLength(fileLength);
            randomAccessFile.close();
            //获取完文件大小之后就断开了连接
            httpURLConnection.disconnect();

            //线程的数量
            int threadSize = 3;
            //计算每个线程的下载量
            int threadLength = (fileLength % 3 == 0 ? fileLength / 3 : fileLength + 1);
            for (int i = 0; i < threadSize; i++) {
                int startPos = i * threadLength;
                RandomAccessFile downloadFile = new RandomAccessFile(fileName, "rwd");
                downloadFile.seek(startPos);
                //启动三条线程从start pos 位置开始下载文件
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class DownloadThread extends Thread {

        private final String mPath;
        private final int mThreadLength;
        private final int mStartPos;
        private final int mThreadId;
        private final RandomAccessFile mRandomAccessFile;


        public DownloadThread(int threadId, int startPos,
                              RandomAccessFile randomAccessFile, int threadLength, String path) {
            this.mThreadId = threadId;
            this.mStartPos = startPos;
            this.mRandomAccessFile = randomAccessFile;
            this.mThreadLength = threadLength;
            this.mPath = path;
        }


        @Override
        public void run() {
            InputStream inputStream = null;
            try {
                URL url = new URL(mPath);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5 * 1000);
                httpURLConnection.setReadTimeout(5 * 1000);
                httpURLConnection.setRequestProperty("Range","byte="+mStartPos+"-");

                if(httpURLConnection.getResponseCode() == 206){
                    inputStream = httpURLConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    int totalLength = 0;
                    while(totalLength<mThreadLength && (len = inputStream.read(buffer))>0){
                        mRandomAccessFile.write(buffer,0,len);
                        totalLength +=len;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(mRandomAccessFile!=null){
                    try {
                        mRandomAccessFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
