package com.xing.apidemo.ani;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

/**
 * Created by zhao on 18-1-16.
 *
 * http://blog.csdn.net/guolin_blog/article/details/43816093
 */

public class PointAniView extends View {

    public static final float RADIUS = 50F;
    private static final String TAG = PointAniView.class.getSimpleName();
    private int mColor;

    private  Paint mPaint;
    private Point mCurrentPaint;
    private ValueAnimator valueAnimator;

    public PointAniView(Context context) {
        super(context);
    }

    public PointAniView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public PointAniView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Keep
    public int getColor(){
        return mColor;
    }

    @Keep
    public void setColor(int color){
        this.mColor = color;
//        try{
//            mPaint.setColor(Color.parseColor(color));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mCurrentPaint == null){
            mCurrentPaint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else{
            drawCircle(canvas);
        }
    }

    private void startAnimation() {
        Point startPoint = new Point(getWidth()/2,RADIUS);
        Point endPoint = new Point(getWidth()/2,getHeight()-RADIUS);
         valueAnimator = ValueAnimator.ofObject(new PointEvalutor(),startPoint,endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point animatedValue = (Point) animation.getAnimatedValue();
                mCurrentPaint = animatedValue;
                Log.i(TAG, "mCurrentPaint " + mCurrentPaint);
                invalidate();
            }
        });
//        valueAnimator.setDuration(5000);
//        valueAnimator.start();

        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this,"color",new ArgbEvaluator(),Color.BLUE,Color.RED);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).with(objectAnimator);
        animatorSet.setDuration(5000);
//        animatorSet.setInterpolator(new AccelerateInterpolator());
        //落地反弹效果
//        animatorSet.setInterpolator(new BounceInterpolator());
        //减速加速效果
        animatorSet.setInterpolator(new DecelerateAccelerateInterpolator());
        animatorSet.start();
    }

    private void drawCircle(Canvas canvas) {
        float x = mCurrentPaint.getX();
        float y = mCurrentPaint.getY();
        canvas.drawCircle(x,y,RADIUS,mPaint);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void pauseAnim(){
        if(valueAnimator!=null){
            valueAnimator.pause();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
//            startAnimation();
        }
        return super.onTouchEvent(event);
    }
}
