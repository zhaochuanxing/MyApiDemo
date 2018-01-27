package com.xing.apidemo.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TimeFormatException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by zhao on 18-1-27.
 */

public class MyScrollView extends View {

    private static final int TIME_SCROLL = 2000;
    private  Scroller mScroller;
    private String Tag = MyScrollView.class.getSimpleName();

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public MyScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.i(Tag,"computeScroll ");
        if(mScroller.computeScrollOffset()){
            View parent = (View) getParent();
            parent.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            Log.i(Tag,"X = "+mScroller.getCurrX()+",y = "+mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX,int destY){
        int deltaX = destX - getScrollX();
        int deltaY = destY - getScrollY();
//             * @param dx Horizontal distance to travel. Positive numbers will scroll the
//     *        content to the left.
//     * @param dy Vertical distance to travel. Positive numbers will scroll the
//                *        content up.
        mScroller.startScroll(getScrollX(),getScrollY(),deltaX,deltaY,TIME_SCROLL);
        //这个调用非常重要，否则无法开启执行滚动
        invalidate();
    }

    public void smoothScrollBy(int deltaX,int deltaY){
        //可以持续的执行连续滚动
        mScroller.startScroll(mScroller.getCurrX(),mScroller.getCurrY(),deltaX,deltaY, TIME_SCROLL);
        //这个调用至关重要
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(Tag,"onDraw");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //这个证明了 view滚动后是在真实的位置上
        Log.i(Tag,"onTouchEvent "+event);
        return super.onTouchEvent(event);
    }
}
