package com.xing.apidemo.okhttp;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xing.apidemo.R;
import com.xing.apidemo.webview.mywebview.MyWebView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        initNetwork();
    }

    private void initView() {
        this.mWebView = (MyWebView)findViewById(R.id.webview_id);
    }

    private void initNetwork() {
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
}
