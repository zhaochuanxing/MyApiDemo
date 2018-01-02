package com.xing.apidemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zhao on 18-1-2.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    public DrawSurfaceView(Context context) {
        super(context);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {

    }
}
