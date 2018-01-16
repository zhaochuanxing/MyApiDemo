package com.xing.apidemo.ani;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhao on 18-1-16.
 */

public class PointAniView extends View {

    public static final float RADIUS = 50F;
    private static final String TAG = PointAniView.class.getSimpleName();

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
        Point startPoint = new Point(RADIUS,RADIUS);
        Point endPoint = new Point(getWidth()-RADIUS,getHeight()-RADIUS);
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
        valueAnimator.setDuration(5000);
        valueAnimator.start();
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
