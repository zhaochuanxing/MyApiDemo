package com.xing.apidemo.okhttp.request;

import com.xing.apidemo.okhttp.utils.ExceptionUtil;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhao on 18-4-4.
 */

public abstract class OkHttpRequest {

    private final String mUrl;
    private final Object mTag;
    private final Map<String, String> mParams;
    private final Map<String, String> mHeaders;
    private final int mId;

    protected Request.Builder mBuilder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, int id) {
        this.mUrl = url;
        this.mTag = tag;
        this.mParams = params;
        this.mHeaders = headers;
        this.mId = id;

        if (url == null) {
            ExceptionUtil.illegalArgument("url can not be null");
        }
        initBuilder();
    }

    /**
     * 初始化一些基本参数:url,tag,headers
     */
    private void initBuilder() {
        mBuilder.url(mUrl).tag(mTag);
        appendHeaders();
    }

    private void appendHeaders() {
        if (mHeaders == null || mHeaders.size() <= 0) {
            return;
        }
        Headers.Builder headBuilder = new Headers.Builder();
        for (String key : mHeaders.keySet()) {
            headBuilder.add(key, mHeaders.get(key));
        }
        mBuilder.headers(headBuilder.build());
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(RequestBody requestBody);

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback){
        return requestBody;
    }

    public RequestCall build(){
        return new RequestCall(this);
    }

    /**
     * 模板方法的设计模式
     * @param callback
     * @return
     */
    public Request generateRequest(Callback callback){
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody,callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }

    public int getId(){
        return mId;
    }

}
