package com.xing.apidemo.glide;

import android.util.Log;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by zhao on 17-11-16.
 */

public class OkHttpFetcher implements DataFetcher<InputStream> {

    private final OkHttpClient mClient;
    private final GlideUrl mGlideUrl;
    private volatile boolean mIsCanceled;
    private InputStream mInputStream;
    private ResponseBody mResponseBody;

    public OkHttpFetcher(OkHttpClient okhttpClient, GlideUrl glideUrl){
        this.mClient = okhttpClient;
        this.mGlideUrl = glideUrl;
        Log.i("zcx","fetcher");
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {
        Request.Builder requestBuilder = new Request.Builder().url(mGlideUrl.toStringUrl());
        for(Map.Entry<String,String> headerEntry:mGlideUrl.getHeaders().entrySet()){
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key,headerEntry.getValue());
        }
        Request request = requestBuilder.build();
        if(mIsCanceled){
            return null;
        }
        Response response = mClient.newCall(request).execute();
        mResponseBody = response.body();
        Log.i("zcx","response = "+mResponseBody);
        if(!response.isSuccessful()||mResponseBody==null){
            throw new IOException("request failed with code"+response.code());
        }
        String md5 = Md5Util.md5(mResponseBody.bytes());
//        Log.i("zcx","md5= "+md5);
        mInputStream = ContentLengthInputStream.obtain(mResponseBody.byteStream(),mResponseBody.contentLength());
        return mInputStream;
    }

    @Override
    public void cleanup() {
        if(mInputStream!=null){
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(mResponseBody!=null){
            mResponseBody.close();
        }

    }

    @Override
    public String getId() {
        return mGlideUrl.getCacheKey();
    }

    @Override
    public void cancel() {
        mIsCanceled = true;
    }
}
