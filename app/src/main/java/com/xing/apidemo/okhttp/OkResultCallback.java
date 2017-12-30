package com.xing.apidemo.okhttp;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhao on 17-12-17.
 */

public interface OkResultCallback {
    public abstract void onError(Request request,Exception e);
    public abstract void onResponse(Response request) throws IOException;
}
