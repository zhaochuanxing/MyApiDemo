package com.xing.apidemo.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import com.xing.apidemo.R;

public class SpecialImageViewActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_image_view);
        mImageView = (ImageView)findViewById(R.id.imgview);
        Drawable drawable = getResources().getDrawable(R.drawable.card_history);
        drawable.setLevel(100);
        ScaleDrawable scaleDrawable = new ScaleDrawable(drawable, Gravity.CENTER,0.1f,0.1f);
        scaleDrawable.setLevel(100);
        mImageView.setImageDrawable(scaleDrawable);

    }
}
