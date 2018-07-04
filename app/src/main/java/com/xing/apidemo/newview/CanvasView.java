package com.xing.apidemo.newview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    private Paint mPaint;
    private Paint mPaintTwo;

    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaintTwo = new Paint();
        mPaintTwo.setColor(Color.BLUE);
        mPaintTwo.setStrokeWidth(10);
        mPaintTwo.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(300,200,mPaint);
        canvas.drawPoints(new float[]{
                600,500,600,600,600,700
        },mPaint);

        canvas.drawLine(100,200,700,300,mPaintTwo);
        canvas.drawLines(new float[]{
                100,200,200,300,
                300,400,300,700
        },mPaint);

        canvas.drawRect(300,600,500,800,mPaintTwo);
        Rect rect = new Rect(100,700,800,900);
        canvas.drawRect(rect,mPaint);
        RectF rectF = new RectF(200,300,400,500);
//        canvas.drawRect(rectF,mPaint);

        canvas.drawRoundRect(rectF,20,20,mPaint);

        RectF rect1 = new RectF(500,1000,800,1200);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(rect1,10,10,mPaintTwo);
        }
        canvas.drawOval(rect1,mPaint);
        
    }
}
