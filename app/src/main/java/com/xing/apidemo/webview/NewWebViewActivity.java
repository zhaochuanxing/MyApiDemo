package com.xing.apidemo.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xing.apidemo.R;

public class NewWebViewActivity extends Activity {
    public static final String TAG = NewWebViewActivity.class.getSimpleName();

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_web_view);
        initView();

    }

    private void initView() {
        mWebView = (WebView)findViewById(R.id.webview_id);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new JsBridgeWebChromeClient());
        String url = "file:///android_asset/webview.html";
        mWebView.loadUrl(url);
    }
}
