package com.xing.apidemo.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by zhao on 17-10-25.
 */

public class JsBridgeWebChromeClient extends WebChromeClient {

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Log.i(NewWebViewActivity.TAG,"msg = "+message);
        JsBridge.register("bridge",BridgeImpl.class);

        JsBridge.callJava(view,message);
        result.confirm("haha");
        return true;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        Log.i(NewWebViewActivity.TAG,"MSG = "+message);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("提示").setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result.confirm();
            }
        }).setCancelable(false).show();
        return true;
    }




}
