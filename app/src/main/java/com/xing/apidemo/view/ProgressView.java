package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhao on 17-12-30.
 */

public class ProgressView extends View {

    private int mLength;
    private int mCircleXY;
    private float mRadius;
    private Paint mCircePaint;
    private Paint mArcPaint;
    private String mShowText;
    private Paint mTextPaint;
    private float mTextSize;

    public ProgressView(Context context) {
        super(context);
        init();
    }


    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCircePaint = new Paint();
        mCircePaint.setColor(Color.RED);
        mArcPaint =new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(2F);
        mShowText = "趋势图来看看";
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextSize = 80f;
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLength = getMeasuredWidth();
        //绘制圆形
        mCircleXY = mLength/2;
        mRadius = (float) ((mLength*0.5)/2);
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCircePaint);

        //绘制弧线
        RectF mArcRectF = new RectF((float)(mLength*0.1),(float)(mLength*0.1),
                (float)(mLength*0.9),(float)(mLength*0.9));
        //从右侧横向为起点，顺时针走
        canvas.drawArc(mArcRectF,0,270.0F,false,mArcPaint);

        //绘制文字 横向纵向居中
        canvas.drawText(mShowText,0,mShowText.length(),mCircleXY-(mTextSize*mShowText.length()/2)
                ,mCircleXY/4,mTextPaint);
        // x 开始绘制的起点
        //y 绘制文字的基线,并不一定是文字的底线，基线下面也会有绘制文字的地方，因此这里选择用/4来表示
        //y的偏移，而不是/2
        //画线来更明显的显示
        canvas.drawLine(0,mCircleXY,mLength,mCircleXY,mArcPaint);
    }
}
