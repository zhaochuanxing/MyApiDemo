package com.xing.apidemo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.xing.apidemo.R;

public class ViewAnimationActivity extends Activity {

    private static final String TAG = ViewAnimationActivity.class.getSimpleName();
    private Button mAlphaBtn;
    private Button mRotateBtn;
    private Button mTranslateBtn;
    private Button mScaleBtn;
    private Button mSetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        initView();
    }

    private void initView() {
        mAlphaBtn = (Button)findViewById(R.id.btn_alpha);
        mAlphaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
                alphaAnimation.setDuration(1000);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i(TAG,"onAnimationStart "+animation);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.i(TAG,"onAnimationEnd ");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.i(TAG,"onAnimationRepeat");
                    }
                });
                alphaAnimation.setRepeatCount(0);
                mAlphaBtn.startAnimation(alphaAnimation);
            }
        });

        mRotateBtn = (Button)findViewById(R.id.btn_rotate);
        mRotateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation rotateAnimation = new RotateAnimation(0,360,0,0);
                RotateAnimation roteself = new RotateAnimation(0,270,RotateAnimation.RELATIVE_TO_SELF,0.5F
                ,RotateAnimation.RELATIVE_TO_SELF,0.5F);
                roteself.setDuration(1000);
                roteself.setFillAfter(true);

                v.startAnimation(roteself);
            }
        });

        mTranslateBtn = (Button)findViewById(R.id.btn_translate);
        mTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(0,200,0,300);
                translateAnimation.setDuration(1000);
                //结束后保持
                translateAnimation.setFillBefore(true);
                translateAnimation.setFillAfter(true);
                v.startAnimation(translateAnimation);
            }
        });

        mScaleBtn = (Button)findViewById(R.id.btn_scale);
        mScaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,2);
                scaleAnimation.setDuration(1000);
                v.startAnimation(scaleAnimation);
                ScaleAnimation scaleSelf = new ScaleAnimation(0,2,0,2,Animation.RELATIVE_TO_SELF,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f);
                scaleSelf.setDuration(1000);
                scaleSelf.setFillEnabled(true);
                scaleSelf.setRepeatMode(Animation.REVERSE);
                scaleSelf.setRepeatCount(Animation.INFINITE);
                v.startAnimation(scaleSelf);
            }
        });

        mSetBtn = (Button)findViewById(R.id.btn_set);
        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setDuration(1000);
                animationSet.setInterpolator(new AccelerateDecelerateInterpolator());

                AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
                alphaAnimation.setDuration(1000);
                animationSet.addAnimation(alphaAnimation);

                RotateAnimation roteself = new RotateAnimation(0,270,RotateAnimation.RELATIVE_TO_SELF,0.5F
                        ,RotateAnimation.RELATIVE_TO_SELF,0.5F);
                roteself.setDuration(1000);
                animationSet.addAnimation(roteself);
                v.startAnimation(animationSet);
            }
        });
    }
}
