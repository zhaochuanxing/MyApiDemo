package com.xing.apidemo.volley;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zhao on 18-3-6.
 */

public class MyRequestQueue {
    private static Context mApp;
    private RequestQueue mRequestQueue;
    private MyRequestQueue(){
        mRequestQueue = Volley.newRequestQueue(mApp);
    }

    private static class Holder{
        private static final MyRequestQueue sInstance = new MyRequestQueue();
    }

    public static MyRequestQueue getInstance(Context context){
        mApp = context.getApplicationContext();
        return Holder.sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }
}
