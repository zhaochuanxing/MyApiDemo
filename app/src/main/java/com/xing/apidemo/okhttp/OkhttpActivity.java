package com.xing.apidemo.okhttp;

import android.app.Activity;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;

import com.xing.apidemo.R;
import com.xing.apidemo.webview.mywebview.MyWebView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkhttpActivity extends Activity {

    private static final String TAG = "okhttp";
    private MyWebView mWebView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
//        postData();
//        uploadFile();
//        downloadFile();
//        uploadMultiPart();
        getDataByUtil();
    }

    private void uploadMultiPart() {
        MediaType mediaType = MediaType.parse("image/png");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title","zhao")
                .addFormDataPart("image","zhao.jpg",RequestBody.create(mediaType,
                        new File("/sdcard/zhao.jpg")))
                .build();
        Request request = new Request.Builder()
                .header("Authorization","Client-ID"+"...")
                .url("http://")
                .post(requestBody)
                .build();

        try {
            Response execute = okHttpClient.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile() {
        Log.i(TAG, "downloadFile");
//        String url = "http://img02.tooopen.com/images/20160509/tooopen_sy_161967094653.jpg";
        String url = "http://img3.redocn.com/tupian/20150318/qingxinshuzhibiankuang_4021000.jpg";
        Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"downloadFile "+call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse " + response);
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = null;
                String filePath = null;
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                } else {
                    filePath = getFilesDir().getAbsolutePath();
                }
                File file = new File(filePath, "zhao.jpg");
                if (file != null) {
                    fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
                }
            }
        });
    }

    private void postData() {
        String ipUrl = "http://ip.taobao.com/service/getIpInfo.php";
        RequestBody formBody = new FormBody.Builder().add("ip","59.108.54.37").build();
        final Request request = new Request.Builder().url(ipUrl)
                .post(formBody)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Log.i(TAG,"ip body "+str);
            }
        });
    }

    private void initView() {
        this.mWebView = (MyWebView)findViewById(R.id.webview_id);
    }

    private void getData() {
//        String url = "http://www.jianshu.com/u/35083fcb7747";
        String url = "http://www.jianshu.com/u/47af1f2e247b";
        Request.Builder requestBuilder = new Request.Builder().url(url)
                .method("GET",null);
        final Request request = requestBuilder.build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"fail "+call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                final ResponseBody body = response.body();
                final String string = body.string();
                Log.i(TAG,"body= "+ string);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadData(string,"text/html;charset=UTF-8",null);
//                        mWebView.loadDataWithBaseURL(null,string,"text/html","utf-8",null);
                    }
                });
                Log.i(TAG,"onResponse"+response.toString());
            }
        });
    }

    public void getDataByUtil() {
        OkHttpEngine instance = OkHttpEngine.getInstance(this);
        String url = "http://www.jianshu.com/u/47af1f2e247b";
        instance.getAsyHttp(url, new OkResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i(TAG,"request fail",e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Log.i(TAG,"getData "+ string);
                mWebView.loadData(string,"text/html;charset=UTF-8",null);
            }
        });
    }
}
