package com.xing.apidemo.view.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xing.apidemo.view.BaseView;
import com.xing.apidemo.view.BaseViewGroup;

/**
 * Created by zhao on 17-12-31.
 */

public class MyView extends BaseView {
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        this.setClickable(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(BaseViewGroup.TAG,"setOnClickListener");

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(BaseViewGroup.TAG,"onTouchEvent "+this+",event = "+event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(BaseViewGroup.TAG,"dispatchTouchEvent "+this+",event = "+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
