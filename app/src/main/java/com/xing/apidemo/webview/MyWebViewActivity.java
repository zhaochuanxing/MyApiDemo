package com.xing.apidemo.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xing.apidemo.R;
import com.xing.apidemo.Util.AndroidTest;

public class MyWebViewActivity extends Activity {

    private static final String TAG = MyWebViewActivity.class.getSimpleName();
    private RelativeLayout mContainerLayout;
    private WebView mWebView;
    private boolean mIsLoadError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);
        initView();
        initWebView();
        loadPage();
//        javaCalljs();
    }

    private void loadPage() {
//        String url = "http://www.bijishequ.com/detail/340926?p=";
        String url = "file:///android_asset/home.html";
        mWebView.loadUrl(url);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void javaCalljs(){
        final String js="javascript:callFromJava('call from java')";
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                mWebView.loadUrl(js);
//            }
//        });

        mWebView.evaluateJavascript(js, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.i(TAG,"onReceiveValue"+value);
            }
        });
    }

    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //设置允许js弹窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(new AndroidTest(MyWebViewActivity.this),"AndroidTest");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i(TAG, "onPageStarted " + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i(TAG, "onPageFinished " + url);
                //JS代码调用一定要在 onPageFinished（） 回调之后才能调用，否则不会调用。
                javaCalljs();
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                Log.i(TAG, "onPageCommitVisible ");
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.i(TAG, "onReceivedError " + request + ",error" + error);

            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i(TAG, "onReceivedTitle " + title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i(TAG, "onProgressChanged " + newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyWebViewActivity.this);
                builder.setTitle("提示").setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).setCancelable(false).show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyWebViewActivity.this);
                builder.setTitle("请您确认").setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                }).setCancelable(false).show();
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
                final EditText editText = new EditText(MyWebViewActivity.this);
                editText.setText(defaultValue);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyWebViewActivity.this);
                builder.setTitle(message).setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm(editText.getText().toString());
                            }
                        }).setCancelable(false).show();
                return true;
            }
        });

        mWebView.setPictureListener(new WebView.PictureListener() {
            @Override
            public void onNewPicture(WebView view, Picture picture) {
                Log.i(TAG, "onNewPicture " + picture);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        mContainerLayout.removeAllViews();
        mWebView.clearHistory();
        mWebView.removeAllViews();
        mWebView.stopLoading();
        mWebView.clearCache(true);
        mWebView.destroy();
        mWebView = null;
    }

    private void initView() {
        mContainerLayout = (RelativeLayout) findViewById(R.id.contianer_layout);
        mWebView = new WebView(MyWebViewActivity.this);
        mContainerLayout.addView(mWebView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            if(mIsLoadError){
                mIsLoadError = false;
                mWebView.goBackOrForward(-2);
            }else{
                mWebView.goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
