package com.xing.apidemo.download;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SingleThreadUtil {

    /**
     * 单线程下载文件
     * @param path
     * @param context
     */
    public static void download(String path,Context context)  {
        URL url = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            url = new URL(path);
            URLConnection urlConnection = (HttpURLConnection)url.openConnection();
             inputStream = url.openStream();
            String fileName = path.substring(path.lastIndexOf("."));
            //打开手机的输出流，输出到文件中
             outputStream = context.openFileOutput("Cache_"+System.currentTimeMillis()+fileName,Context.MODE_PRIVATE);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
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
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
