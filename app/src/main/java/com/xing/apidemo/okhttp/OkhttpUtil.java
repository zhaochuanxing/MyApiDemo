package com.xing.apidemo.okhttp;

import com.xing.apidemo.glide.OkHttpGlideUrlLoader;
import com.xing.apidemo.okhttp.utils.Platform;

import java.util.concurrent.Executor;

import okhttp3.OkHttpClient;

/**
 * Created by zhao on 18-4-4.
 */

public class OkhttpUtil {

    private final Platform mPlatform;
    private OkHttpClient mOkHttpClient;
    private volatile static OkhttpUtil sInstance;

    private OkhttpUtil(OkHttpClient okHttpClient){
        if(okHttpClient==null){
            mOkHttpClient = new OkHttpClient();
        }else{
            mOkHttpClient = okHttpClient;
        }
        mPlatform = Platform.get();
    }

    public static OkhttpUtil getInstance(OkHttpClient okHttpClient){
        if(sInstance == null){
            synchronized (OkHttpClient.class){
                if(sInstance==null){
                    sInstance = new OkhttpUtil(okHttpClient);
                }
            }
        }
        return sInstance;
    }

    public static OkhttpUtil getInstance(){
        return getInstance(null);
    }

    public Executor getDelivery(){
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }




}
