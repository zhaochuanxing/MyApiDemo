package com.xing.apidemo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import com.xing.apidemo.R;

public class ViewAnimationActivity extends Activity {

    private Button mAlphaBtn;

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

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
//                mAlphaBtn.setAnimation(alphaAnimation);
                mAlphaBtn.startAnimation(alphaAnimation);
            }
        });
    }
}
