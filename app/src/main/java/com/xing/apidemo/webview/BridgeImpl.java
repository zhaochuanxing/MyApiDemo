package com.xing.apidemo.webview;

import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhao on 17-10-25.
 */

public class BridgeImpl implements IBridge {

    public static void showToast(WebView webView, JSONObject param,WebViewCallBack webViewCallBack){
        String message = param.optString("msg");
        Toast.makeText(webView.getContext(),message,Toast.LENGTH_SHORT).show();
        if(webViewCallBack!=null){
            try {
                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("key","zhao");
                jsonObject.put("value",1);
                webViewCallBack.apply(getJsonObject(0,"ok",jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONObject getJsonObject(int code, String msg, JSONObject jsonObject) {
        try {
            JSONObject jsonResult = new JSONObject();
            jsonObject.put("code",code);
            jsonObject.put("msg",msg);
            jsonObject.putOpt("result",jsonObject);
            return jsonResult;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
