package com.xing.apidemo.material;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;

import com.xing.apidemo.R;

public class TintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tint);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        AppCompatImageView imageView = (AppCompatImageView)findViewById(R.id.imageview);
        //默认值：  mDrawableTintMode = PorterDuff.Mode.SRC_ATOP; 与porterduff中的一致
        imageView.setSupportBackgroundTintMode(PorterDuff.Mode.ADD);
//        imageView.setSupportBackgroundTintList(new ColorStateList());
    }
}
