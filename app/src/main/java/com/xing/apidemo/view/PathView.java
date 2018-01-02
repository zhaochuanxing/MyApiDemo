package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zhao on 18-1-2.
 */

public class PathView extends BaseView {
    private Path mPath;
    private Paint mPaint;
    private PathEffect[] mEffects;

    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPath = new Path();
        mPath.moveTo(0,0);
        for(int i=0;i<=30;i++){
            mPath.lineTo(i*35, (float) (Math.random()*100));
        }

        mEffects = new PathEffect[5];
        mEffects[0] = null;
        mEffects[1]=new CornerPathEffect(30);
        mEffects[2] = new DiscretePathEffect(3.0f,4.0f);
        mEffects[3]= new DashPathEffect(new float[]{20,10,5,10},0);
        //组合
        mEffects[4]=new ComposePathEffect(mEffects[1],mEffects[3]);
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制path时，paint不能为空
        canvas.drawColor(Color.WHITE);
//        canvas.drawPath(mPath,mPaint);
        for(int i=0;i<mEffects.length;i++){
            mPaint.setPathEffect(mEffects[i]);
            canvas.drawPath(mPath,mPaint);

            //绘制一次，将绘制的原点下移200像素，相当于将坐标系整体的下移200px
            canvas.translate(0,200);
        }
    }
}
