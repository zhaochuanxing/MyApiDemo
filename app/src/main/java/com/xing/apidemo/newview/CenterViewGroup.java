package com.xing.apidemo.newview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CenterViewGroup extends ViewGroup {

    public CenterViewGroup(Context context) {
        super(context);
    }

    public CenterViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMeasure = 0;
        int heightMeasure = 0;
        //1. 遍历所有的子view
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //2
        //3.
        setMeasuredDimension(widthMeasureSpec,widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            int left = (r-width)/2;
            int top = (b-height)/2;
            int right = left+width;
            int bottom = top+height;

            child.layout(left,top,right,bottom);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


}
