package com.xing.apidemo.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zhao on 17-12-16.
 */

public class VolleyUtil {

    private volatile static VolleyUtil sVolleyUtil;
    private RequestQueue mRequestQueue;
    private VolleyUtil(Context context){
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static VolleyUtil getInstance(Context context) {
        if (sVolleyUtil == null) {
            synchronized (VolleyUtil.class) {
                if (sVolleyUtil == null) {
                    sVolleyUtil = new VolleyUtil(context);
                }
            }
        }
        return sVolleyUtil;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

}
