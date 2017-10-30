package com.xing.apidemo.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
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

import java.util.HashMap;
import java.util.Set;

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
    private void javaCalljs() {
        final String js = "javascript:callFromJava('call from java')";
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                mWebView.loadUrl(js);
//            }
//        });

        mWebView.evaluateJavascript(js, new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.i(TAG, "onReceiveValue" + value);
            }
        });
    }

    private void initWebView() {
        if (Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT < 17) {
            fixWebView();
        }
        WebSettings webSettings = mWebView.getSettings();
//        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //设置允许js弹窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(new AndroidTest(MyWebViewActivity.this), "AndroidTest");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if ("js".equals(uri.getScheme())) {
                    Set<String> queryParameterNames = uri.getQueryParameterNames();
                    HashMap<String, String> paramMap = new HashMap<String, String>();
                    for (String param : queryParameterNames) {
                        String queryParameter = uri.getQueryParameter(param);
                        paramMap.put(param, queryParameter);
                        Log.i(TAG, "queryParameter =" + queryParameter);
                    }
                    String authority = uri.getAuthority();
                    Toast.makeText(mWebView.getContext(), authority, Toast.LENGTH_LONG).show();
//                    if ("webview".equals(uri.getAuthority())) {
//                        Log.i(TAG, " call android by uri");
//                        return true;
//                    } else if ("category".equals(authority)) {
//                        Log.i(TAG, "call category" + paramMap.get("id"));
//                    } else if ("pay".equals(authority)) {
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                //do pay things;
//                                Log.i(TAG,"pay success");
//                                mWebView.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        String payUri = "javascript:callFromJava('pay success')";
//                                        mWebView.loadUrl(payUri);
//                                    }
//                                });
//                            }
//                        }).start();
//                    }
                    return true;
                }
//                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
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
//                javaCalljs();
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
                Log.i(TAG, "onJsPrompt " + ",url=" + url + ",msg=" + message + ",default=" + defaultValue + ",result= " + result);
                Uri uri = Uri.parse(message);
                if ("vodbridge".equals(uri.getScheme())) {
                    String authority = uri.getAuthority();
                    Toast.makeText(mWebView.getContext(), authority, Toast.LENGTH_LONG).show();

                    HashMap<String, String> paramMap = new HashMap<String, String>();
                    Set<String> queryParameterNames = uri.getQueryParameterNames();
                    for (String param : queryParameterNames) {
                        String queryParameter = uri.getQueryParameter(param);
                        paramMap.put(param, queryParameter);
                        Log.i(TAG, "param = " + queryParameter);
                    }

                    if ("webview".equals(authority)) {
                        Log.i(TAG, " call android by uri");
                        return true;
                    } else if ("category".equals(authority)) {
                        Log.i(TAG, "call category" + paramMap.get("id"));
                    } else if ("pay".equals(authority)) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG,"pay success");
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                mWebView.post(new Runnable() {
                                    @Override
                                    public void run() {
//                                        String payUri = "javascript:callFromJava('pay success')";
//                                        mWebView.loadUrl(payUri);
                                        MyWebViewActivity.this.javaCalljs();

                                    }
                                });
                            }
                        }).start();
                    }else if("search".equals(authority)){
                        Log.i(TAG,"goto search");
                    }

                    result.confirm("成功返回");
                    return true;
                }

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
        mContainerLayout.removeAllViews();
        mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
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
        mContainerLayout.addView(mWebView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            if (mIsLoadError) {
                mIsLoadError = false;
                mWebView.goBackOrForward(-2);
            } else {
                mWebView.goBack();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @TargetApi(11)
    private void fixWebView() {
        //        http://50.56.33.56/blog/?p=314
        //        http://drops.wooyun.org/papers/548
        // We hadn't use addJavascriptInterface, but WebView add "searchBoxJavaBridge_" to mJavaScriptObjects below API 17 by default:
        // mJavaScriptObjects.put(SearchBoxImpl.JS_INTERFACE_NAME, mSearchBox);
        mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
    }
}
