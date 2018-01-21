package com.xing.apidemo.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xing.apidemo.R;
import com.xing.apidemo.okhttp.OkhttpActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class OkHttpRxActivity extends AppCompatActivity {

    private static final String TAG = OkhttpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_rx);
        postAsynHttp("144.0.9.250");

    }

    private void postAsynHttp(String ip){
        Observable<String> observable = getObservable(ip);
        observable
                .subscribeOn(Schedulers.io())//在io线程上 observable自身在io线程上，执行网络请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程上 跑observer执行的程序，更新ui需要在主线程上
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG,"onCompleted ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError ",e);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG,"onNext "+s);
                    }
                });
    }

    private Observable<String> getObservable(final String ip){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                OkHttpClient okHttpClient = new OkHttpClient();
//                RequestBody requestBody = new FormBody.Builder().add("ip",ip).build();
//                final Request postRequest = new Request.Builder()
//                        .url("http://ip.taobao.com/service/getIpInfo.php")
//                        .post(requestBody)
//                        .build();

                //get 参数
                String url = "http://ip.taobao.com/service/getIpInfo.php";
                Request.Builder requestBuilder = new Request.Builder();
                HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
                urlBuilder.addQueryParameter("ip",ip);
                requestBuilder.url(urlBuilder.build());
                Request getRequest = requestBuilder.build();
//
                Call call = okHttpClient.newCall(getRequest);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        //主动设置发送事件和结束事件
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                });
            }
        });
        return observable;
    }
}
