package com.xing.apidemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zhao on 17-12-31.
 */

public class ScollViewGroup extends ViewGroup {

    private int mScreenHeight;
    private int mLastY;
    private int mStart;
    private Scroller mScroller;
    private int mEnd;

    public ScollViewGroup(Context context) {
        super(context);
        init();
    }

    public ScollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mScroller = new Scroller(getContext());
    }

    //遍历的方式来通知子view对自身进行测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for(int i=0;i<count;i++){
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
        mScreenHeight = getHeight();
        int childCount = getChildCount();
        marginLayoutParams.height = mScreenHeight*childCount;
        setLayoutParams(marginLayoutParams);
        for(int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                childView.layout(0,i*mScreenHeight,getWidth(),mScreenHeight*(i+1));
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLastY -y;
                if(getScrollY()<0){
                    dy = 0;
                }
                if(getScrollY()>getHeight()-mScreenHeight){
                    dy = 0;
                }
                scrollBy(0,dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd - mStart;
                if(dScrollY>0){//向下滑动
                    if(dScrollY<mScreenHeight/3){//滚动距离不足1/3,则要滚动回去
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{// 否则直接滚动到下一页
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }
                }else{//向上滑动
                    if(-dScrollY<mScreenHeight/3){//滚动回原处
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }

    }
}
