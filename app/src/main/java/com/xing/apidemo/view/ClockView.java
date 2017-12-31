package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zhao on 17-12-31.
 */

public class ClockView extends BaseView {
    private int mWidth;
    private int mHeight;
    private Paint mCirclePaint;
    private Paint mDegreePaint;
    private Paint mTextPaint;
    private Paint mHourPaint;
    private Paint mMinutePaint;
    private Paint mSecondPaint;
    private int mCount;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setColor(Color.WHITE);
        mCirclePaint.setAntiAlias(false);
        mCirclePaint.setStrokeWidth(5);

        mDegreePaint = new Paint();
        mDegreePaint.setStyle(Paint.Style.STROKE);
        mDegreePaint.setColor(Color.WHITE);
        mDegreePaint.setStrokeWidth(3);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.YELLOW);

        mHourPaint = new Paint();
        mHourPaint.setStrokeWidth(20);
        mHourPaint.setStyle(Paint.Style.FILL);
        mHourPaint.setColor(Color.WHITE);
        mMinutePaint = new Paint();
        mMinutePaint.setStrokeWidth(10);
        mMinutePaint.setColor(Color.WHITE);

        mSecondPaint = new Paint();
        mSecondPaint.setColor(Color.WHITE);
        mSecondPaint.setStrokeWidth(3);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,mCirclePaint);

        for(int i=0;i<24;i++){
            if(i==0|| i==6|| i==12|| i==18){
                mDegreePaint.setStrokeWidth(5);
                mTextPaint.setTextSize(25);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,
                        mWidth/2,mHeight/2-mWidth/2+60,mDegreePaint);
                String degree = String.valueOf(i);
                canvas.drawText(degree,mWidth/2-mTextPaint.measureText(degree)/2,
                        mHeight/2-mWidth/2+90,mTextPaint);
            }else{
                mDegreePaint.setStrokeWidth(3);
                mTextPaint.setTextSize(15);
                //设置绘制的起点是横向为屏幕宽度一半，纵向为圆心向上移动半径的距离，也就是在顶部的圆形图上
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,
                        mWidth/2,mHeight/2-mWidth/2+30,
                        mDegreePaint);
                String degree = String.valueOf(i);
                canvas.drawText(degree,mWidth/2-mTextPaint.measureText(degree)/2,
                        mHeight/2-mWidth/2+60,mTextPaint);
            }

            // 以圆心为标准，旋转画布15度
            canvas.rotate(15,mWidth/2,mHeight/2);
        }

        // 画指针
        canvas.save();
        //平移坐标原点，将其从0,0移动到圆心的位置上，此后的drawline的参数将会是以新的坐标原点开始的
        canvas.translate(mWidth/2,mHeight/2);
//        canvas.drawLine(0,0,150,0,mHourPaint);
//        canvas.drawLine(0,0,100,300,mMinutePaint);
        canvas.rotate(15*mCount);
        canvas.drawLine(0,0,0,400,mSecondPaint);
        canvas.restore();
        mCount ++;
        postInvalidateDelayed(800);
    }
}
