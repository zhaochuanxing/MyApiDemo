package com.xing.apidemo.newvolley;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xing.apidemo.R;

public class NewVolleyActivity extends AppCompatActivity {

    public static final String TAG = "NewVolleyActivity";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_volley);
        Button strRequest = findViewById(R.id.btn_request);
        strRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestStringData();
            }
        });

        Button btnImg = findViewById(R.id.btn_img);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImg();
            }
        });
        mImageView = (ImageView)findViewById(R.id.imageView);

    }

    private void requestStringData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.baidu.com/";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG,"requestStringData: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"onErrorResponse: "+error);

            }
        });
        queue.add(stringRequest);

    }

    private void requestImg(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String picUrl = "";
        ImageRequest imageRequest = new ImageRequest(picUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                mImageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"onErrorResponse"+error);
                    }
                });
        queue.add(imageRequest);

    }

    private void imageLoad(){
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue,new BitmapCache());
        String picUrl = "http://img1.imgtn.bdimg.com/it/u=2973069531,657782944&fm=26&gp=0.jpg";
        imageLoader.get(picUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                mImageView.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
