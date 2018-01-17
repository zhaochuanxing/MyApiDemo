package com.xing.apidemo.aty;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xing.apidemo.MyApplication;
import com.xing.apidemo.R;
import com.xing.apidemo.ani.MyAnimation;
import com.xing.apidemo.ani.RotateAnimation;

public class ProPerAniActivity extends Activity {

    private static final String TAG = ProPerAniActivity.class.getSimpleName();
    private Button mOneBtn;
    private boolean mFlag;
    private TextView mContentTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_per_ani);
        mOneBtn = (Button)findViewById(R.id.btn_one);
        mContentTx = (TextView)findViewById(R.id.tx_content);

        mFlag = false;
        mOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doAnimation(v);
//                alphaAnimation(v);
//                valueAnimation();
//                rotateAnimation(v);
//                translateAni(mContentTx);
//                scaleAni(mContentTx);
//                animationSet(mContentTx);
//                loadXml(mContentTx);
//                valuesAni(mContentTx);
//                viewAnimate(mContentTx);
                myAnimate(mContentTx);
            }
        });
    }

    private void myAnimate(View view){
        if(view!=null){
            MyAnimation myAnimation = new MyAnimation();
            RotateAnimation rotateAnimation = new RotateAnimation(90, 180,  310.0f, true);
            view.startAnimation(rotateAnimation);
        }
    }

    private void viewAnimate(View view){
        if(view!=null){
            view.animate()
                    .alpha(0)
                    .y(300)
//                    .alpha(1)
//                    .y(-300)

                    .setDuration(2000)
                    .withStartAction(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG,"start ");
                        }
                    }).withEndAction(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG,"end ");
                }
            }).start();

        }
    }

    private void valuesAni(View view){
        PropertyValuesHolder trans = PropertyValuesHolder.ofFloat("translationX",300f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1f,0.1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1f,0.1f);
        ObjectAnimator.ofPropertyValuesHolder(view,trans,scaleX,scaleY)
                .setDuration(3000)
                .start();
    }


    private void animationSet(View view){
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(view,"translationX",-500,0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view,"rotation",0f,360f);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view,"alpha",1f,0f,1f);
        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(rotate).after(fadeOut).before(moveIn);
//        animatorSet.playTogether(moveIn,rotate,fadeOut);
        animatorSet.playSequentially(moveIn,rotate,fadeOut);
        //对每一个动画起作用
        animatorSet.setDuration(3000);
        animatorSet.start();

    }

    private void alphaAnimation(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"alpha",1f,0f,1f);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }

    private void translateAni(View view){
        float translationX = view.getTranslationX();
        Log.i(TAG,"translationX  ="+translationX);
        //参数列表中的最后一个都是指的是目标值，如果制定了初始值，则就是初始值
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"translationX",200);
        objectAnimator.setDuration(1000);
        addListener(objectAnimator);
        objectAnimator.start();
    }

    private void scaleAni(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"scaleY",1f,3f,1f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

    }

    private void rotateAnimation(View view){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"rotation",0f,360f);
        objectAnimator.setDuration(1000);
        addListener(objectAnimator);
        objectAnimator.start();
    }

    private void addListener(ObjectAnimator objectAnimator){
        if(objectAnimator!=null){
            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.i(TAG,"onAnimationUpdate "+animation.getAnimatedValue());
                }
            });


            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    super.onAnimationCancel(animation);
                    Log.i(TAG,"onAnimationCancel "+animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    Log.i(TAG,"onAnimationEnd");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                    Log.i(TAG,"onAnimationRepeat");
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    Log.i(TAG,"onAnimationStart");
                }

                @Override
                public void onAnimationPause(Animator animation) {
                    super.onAnimationPause(animation);
                    Log.i(TAG,"onAnimationPause");
                }

                @Override
                public void onAnimationResume(Animator animation) {
                    super.onAnimationResume(animation);
                    Log.i(TAG,"onAnimationResume");
                }
            });
        }
    }

    private void doAnimation(View view) {
        //控制的是x坐标的目的地
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,"translationX",mFlag?0:300);
        objectAnimator.setDuration(1000);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                //每16ms调用一次,每16ms显示一真
                Log.i(TAG, "animatedValue " + animatedValue);
            }
        });
        objectAnimator.start();
        mFlag = !mFlag;
    }

    private void loadXml(View view){
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.ani_set);
        animator.setTarget(view);
        animator.start();

    }

    private void valueAnimation(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f,0f,1f);
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,50,255,0);
        valueAnimator.setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
               Object value = animation.getAnimatedValue();
               mContentTx.setAlpha((float)value);
               Log.i(TAG,"animation end "+value);
            }
        });
        valueAnimator.start();
    }
}
