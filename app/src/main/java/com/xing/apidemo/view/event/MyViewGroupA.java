package com.xing.apidemo.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xing.apidemo.view.BaseViewGroup;

/**
 * Created by zhao on 17-12-31.
 */

public class MyViewGroupA extends BaseViewGroup {

    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
