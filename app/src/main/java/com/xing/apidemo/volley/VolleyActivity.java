package com.xing.apidemo.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xing.apidemo.R;
import com.xing.apidemo.webview.WebViewCallBack;

import org.json.JSONObject;

public class VolleyActivity extends AppCompatActivity {

    private TextView mContentTx;
    private NetworkImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        initMyView();

//        getNetWorkData();
        getIpInfo();
        loadImg();

    }

    private void getIpInfo() {
        String ipUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=112.226.240.45";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ipUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String s = response.toString();
                        mContentTx.setText(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mContentTx.setText(error.getLocalizedMessage());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
//        VolleyUtil.getInstance(this).getRequestQueue().add(jsonObjectRequest);
    }

    private void initMyView() {
        mContentTx = (TextView)findViewById(R.id.tx_content);
        mImageView = (NetworkImageView)findViewById(R.id.img_net);
    }

    private void loadImg() {
        mImageView.setDefaultImageResId(R.drawable.shape_tip);
        mImageView.setErrorImageResId(0);
        RequestQueue requestQueue = VolleyUtil.getInstance(this).getRequestQueue();
        ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        mImageView.setImageUrl("http://img.taopic.com/uploads/allimg/120727/201995-120HG1030762.jpg",imageLoader);
    }

    private void getNetWorkData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://www.baidu.com";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mContentTx.setText(response);
//                WebView webView = new WebView(VolleyActivity.this);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mContentTx.setText(error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

}
