package com.xing.apidemo.webview;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * Created by zhao on 17-10-25.
 */

public class WebViewCallBack {

    private final WeakReference<WebView> mWebViewRef;
    private final String mPort;
    private final Handler mHandler;
    private static final String CALLBACK_JS_FORMAT = "javascript:JSBridge.onFinish('%s', %s);";


    public WebViewCallBack(WebView webView ,String port){
        mWebViewRef = new WeakReference<WebView>(webView);
        this.mPort = port;
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void apply(JSONObject jsonObject ){
        final String execJs = String.format(CALLBACK_JS_FORMAT,mPort,String.valueOf(jsonObject));
        if(mWebViewRef!=null && mWebViewRef.get()!=null){
            final WebView webView = mWebViewRef.get();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(webView!=null){
                        webView.loadUrl(execJs);
                    }
                }
            });
        }
    }
}
