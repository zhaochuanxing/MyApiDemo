package com.xing.apidemo.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.xing.apidemo.view.BaseView;
import com.xing.apidemo.view.BaseViewGroup;

/**
 * Created by zhao on 18-1-29.
 */

public class HorizontalView extends ViewGroup {

    private final Context mContext;
    private int mLastInterceptX;
    private int mLastInterceptY;
    private int mLastX;
    private int mlastY;
    private int mCurrentIndex;
    private int mChildWidth;
    private Scroller mScroller;
    private VelocityTracker mTracker;

    public HorizontalView(Context context) {
        this(context,null);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mScroller = new Scroller(mContext);
        mTracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //如果没有子元素，设置宽高为0
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            //是在viewgroup内部，view内部，还是activity fragment中对view的调用
            //这个都是不同的写法和思路

            // 宽和高都是at_most,宽度设置为所有子元素的宽度的和
            // 高度设置为第一个子元素的高度
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), childHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //宽度是wrap_content，则宽度是所有子元素的宽度之和
            int childWidth = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(childWidth * getChildCount(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //高度是 wrap_content这个就相当于是让viewgroup自己确定高度，这里
            //其实是可以任意去设置的，这里选择了用第一个view的高度
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize, childHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child != null && child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                int height = child.getMeasuredHeight();
                child.layout(left, 0, left + width, height);
                left += width;
            }

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastInterceptX;
                int deltaY = y - mLastInterceptY;
                if (Math.abs(deltaX) < Math.abs(deltaY)) {
                    //表示是横向移动
                    intercept = true;
                }
                break;
            default:
                break;
        }
        mLastInterceptX = x;
        mLastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-mLastX;
                //执行横向滑动
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - mCurrentIndex * mChildWidth;
                if(Math.abs(distance)>mChildWidth/2){
                    //滑动距离大于子view宽度的1/2，则切换到下一个页面
                    if(distance>0){
                        mCurrentIndex++;
                    }else{
                        mCurrentIndex--;
                    }
                }else{
                    mTracker.computeCurrentVelocity(1000);
                    //获得x方向的分速度
                    float xV = mTracker.getXVelocity();
                    if(Math.abs(xV)>50){
                        //切换到上一个页面
                        if(xV>0){
                            mCurrentIndex--;
                        }else{
                            mCurrentIndex++;
                        }
                    }

                }
                if(mCurrentIndex<0){
                    mCurrentIndex=0;
                }else if(mCurrentIndex>getChildCount()-1){
                    mCurrentIndex = getChildCount()-1;
                }
                smoothScrollTo(mCurrentIndex * mChildWidth,0);
                mTracker.clear();
                break;
            default:
                break;
        }
        mLastX = x;
        mlastY = y;
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        mScroller.startScroll(getScrollX(),getScrollY(),destX-getScrollX(),destY-getScrollY(),1000);
        invalidate();
    }
}
