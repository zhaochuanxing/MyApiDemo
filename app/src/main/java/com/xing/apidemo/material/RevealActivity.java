package com.xing.apidemo.material;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.xing.apidemo.R;

public class RevealActivity extends AppCompatActivity {

    private ImageView mRectImg;
    private ImageView mCircleImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        initView();
    }

    private void initView() {
        mRectImg = (ImageView)findViewById(R.id.img_rect);
        mRectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(mRectImg,0,0,0,(float)Math.hypot(mRectImg.getWidth(),mRectImg.getHeight()));
                    animator.setInterpolator(new AccelerateDecelerateInterpolator());
                    animator.setDuration(1000);
                    animator.start();

                }
            }
        });
        mCircleImg = (ImageView)findViewById(R.id.img_circle);
        mCircleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator animator = ViewAnimationUtils.createCircularReveal(mCircleImg,mCircleImg.getWidth()/2,mCircleImg.getHeight()/2,mCircleImg.getWidth(),0);
                    animator.setDuration(1000);
//                    animator.setupEndValues();
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mCircleImg.setVisibility(View.INVISIBLE);
                        }
                    });
                    animator.start();
                }
            }
        });
    }
}
