package com.xing.apidemo.okhttp.request;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by zhao on 18-4-4.
 *
 * 对上一个类OkHttpRequest的封装,对外提供更多的接口
 */

public class RequestCall {

    private final OkHttpRequest mOkHttpRequest;
    private long mReadTimeOut;
    private long mWriteTimeOut;
    private long mConnectTimeOut;
    private Request mRequest;


    public RequestCall(OkHttpRequest okHttpRequest) {
        this.mOkHttpRequest = okHttpRequest;
    }

    //方便采用链式调用,返回自身对象引用
    public RequestCall readTimeOut(long readTimeOut){
        this.mReadTimeOut = readTimeOut;
        return this;
    }

    public RequestCall writeTimeOut(long writeTimeOut){
        this.mWriteTimeOut = writeTimeOut;
        return this;
    }

    public RequestCall connectTimeOut(long connectTimeOut){
        this.mConnectTimeOut = connectTimeOut;
        return this;
    }


}
