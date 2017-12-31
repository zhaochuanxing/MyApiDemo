package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhao on 17-12-31.
 */

public class SoundShapeView extends BaseView {

    private int mRectWidth;
    private int mRectHeight;
    private int mRectCount;
    private int mWidth;
    private int offset;
    private float mCurrentHeight;
    private Paint mPaint;
    private LinearGradient mLinearGradient;

    public SoundShapeView(Context context) {
        this(context,null);
    }

    public SoundShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SoundShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mRectWidth = 20;
        mRectHeight = 200;
        mRectCount = 30;
        offset = 10;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mRectHeight = getHeight();
        mRectWidth = (int) (mWidth*0.6/mRectCount);
        mLinearGradient = new LinearGradient(0,0,mRectWidth,mRectHeight,Color.YELLOW,Color.BLUE,
                Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mRectCount; i++) {
            mCurrentHeight = (float) (Math.random()*mRectHeight);
            canvas.drawRect((float)(mWidth*0.4/2+mRectWidth*i+offset),mCurrentHeight,
                    (float)(mWidth*0.4/2+mRectWidth*(i+1)),mRectHeight,mPaint);
        }
        postInvalidateDelayed(200);
    }
}
