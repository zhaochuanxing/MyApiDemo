package com.xing.apidemo.Util;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by zhao on 17-10-23.
 */

public class AndroidTest {

    private final Context mContext;

    public AndroidTest(Context context){
        this.mContext = context;
    }

    private static final String TAG = AndroidTest.class.getSimpleName();

    @JavascriptInterface
    public String show(String str){
        Log.i(TAG,"show "+str);
        return "come on";
    }

    @JavascriptInterface
    public String showLog(){
        Log.i(TAG,"haha");
        return "";
    }
}
