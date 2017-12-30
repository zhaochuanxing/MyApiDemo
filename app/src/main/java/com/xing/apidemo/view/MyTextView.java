package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zhao on 17-12-30.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView{

    private Paint mOutPaint;
    private Paint mInnerPaint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /*
      在构造方法中完成必要对象的初始化工作，初始化画笔等事情
     */
    private void init() {
        mOutPaint = new Paint();
        mOutPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeWidth(3);

        mInnerPaint = new Paint();
        mInnerPaint.setColor(Color.YELLOW);
        mInnerPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制外层矩形
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mOutPaint);
        // 绘制内层矩形
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mInnerPaint);
        canvas.save();
        canvas.translate(10,0);

        // 在回调父类方法前，实现自己的逻辑，在绘制文本前，做事情
        super.onDraw(canvas);
        //在回调父类方法后，在绘制文本后，做事情
        canvas.restore();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

}
