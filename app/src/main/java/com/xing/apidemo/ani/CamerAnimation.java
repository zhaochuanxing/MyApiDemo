package com.xing.apidemo.ani;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

/**
 * Created by zhao on 18-1-17.
 * http://blog.csdn.net/Listening_music/article/details/6610285
 */

public class CamerAnimation extends Animation {

    private int mCenterHeight;
    private int mCenterWidth;
    private Camera mCamera;
    private float mFromDegrees;
    private float mToDegrees;
    private float mCenterX;
    private float mCenterY;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(2000);
        setFillAfter(true);
        setInterpolator(new BounceInterpolator());
        mCenterWidth = width/2;
        mCenterHeight = height/2;
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
//        Matrix matrix = t.getMatrix();
//        mCamera.save();
//        mCamera.getMatrix(matrix);
//        mCamera.restore();

    }
}
