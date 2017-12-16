package com.xing.apidemo.glide;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.xing.apidemo.R;

public class GlideDemoActivity extends Activity {

    private static final String TAG = GlideDemoActivity.class.getSimpleName();
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        initView();
    }

    private void initView() {
        this.mImageView = (ImageView) findViewById(R.id.imgview);
        String picUrl = "http://pic.ffpic.com/files/tupian/tupian439.jpg";
//        String picUrl = "http://img3.redocn.com/tupian/20151026/xingkongshejitupian_5190232.jpg";
        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                String md5 = Md5Util.getMd5(resource);
                Log.i("zcx", "ready md5 " + md5);
                mImageView.setImageBitmap(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                Log.i("zcx","onload fail",e);
            }

            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
            }
        };

        ViewTarget<ImageView, Bitmap> viewBitmapViewTarget = new ViewTarget<ImageView, Bitmap>(mImageView) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

            }
        };

        Glide.with(this).load(picUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).into(simpleTarget);
    }
}
