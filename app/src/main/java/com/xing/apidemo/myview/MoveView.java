package com.xing.apidemo.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhao on 18-1-27.
 */

public class MoveView extends View {

    private static final String TAG = MoveView.class.getSimpleName();
    private float mLastY;
    private float mLastX;

    public MoveView(Context context) {
        super(context);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,"onTouchEvent "+event);
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        if(action == MotionEvent.ACTION_DOWN){
            mLastX = x;
            mLastY = y;
        }else if(action == MotionEvent.ACTION_MOVE){
            int offsetX = (int) (x - mLastX);
            int offsetY = (int) (y-mLastY);
            //进行view的布局,设置view的矩形区域，view会调用onLayout
//            layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

            //这种调用方式中，不会调用onLayout方法
            //设置right left的横向偏移
//            offsetLeftAndRight(offsetX);
//            //设置top bottom的纵向偏移
//            offsetTopAndBottom(offsetY);


            //改变布局参数
            //对view的layoutparam设置 margin的方式来调整view的位置
            //需要用marginLayoutParams，或者其子类 linearLayout.LayoutParams,RelativeLayout.LayoutParams
            //这种调用方式，会让view回调onLayout,相当于父view会让子view重新布局
            //对于constraintLayout 即使是用ConstraintLayout.MarginLayoutParams,也是不可以的
//            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//            marginLayoutParams.leftMargin = getLeft()+offsetX;
//            marginLayoutParams.topMargin = getTop()+offsetY;
//            setLayoutParams(marginLayoutParams);


            //设置滑动的方式scroll
            //这种方式不会调用onLayout
            View parent = (View) getParent();
            parent.scrollBy(-offsetX,-offsetY);

        }
        //需要设置为return true,如果是return super，则不会有效果的。
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG,"onLayout");
    }
}
