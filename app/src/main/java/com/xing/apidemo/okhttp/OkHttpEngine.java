package com.xing.apidemo.okhttp;

import android.content.Context;
import android.os.Handler;
import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.xml.transform.OutputKeys;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhao on 17-12-17.
 */

public class OkHttpEngine {

    private static final String TAG = "OkhttpEngine";
    private final OkHttpClient mOkHttpClient;
    private volatile static OkHttpEngine mOkHttpEngine;
    private final Handler mHandler;

    public static OkHttpEngine getInstance(Context context){
        if(mOkHttpEngine==null){
            synchronized (OkHttpEngine.class){
                if(mOkHttpEngine==null){
                    mOkHttpEngine = new OkHttpEngine(context);
                }
            }
        }
        return mOkHttpEngine;
    }

    private OkHttpEngine(Context context) {
//        File sdCache = context.getExternalCacheDir();
        File sdCache = context.getCacheDir();
        Log.i(TAG, "cache file = " + sdCache.getAbsolutePath());
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(), cacheSize));

//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return null;
//                    }
//                });
        mOkHttpClient = builder.build();
        mHandler = new Handler();
    }

    public void getAsyHttp(String url, OkResultCallback callBack){

        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        dealRequest(call,callBack);

    }

    private void dealRequest(Call call, final OkResultCallback callBack) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(callBack!=null){
                    callBack.onError(call.request(),e);
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(callBack!=null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callBack.onResponse(response);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

}
