package com.xing.apidemo.ani;

import android.graphics.Matrix;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zhao on 18-1-17.
 */

public class MyAnimation extends Animation {

    private int mCenterWidth;
    private int mCenterHeight;

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        //让图片view纵向比例不断缩小
        final Matrix matrix = t.getMatrix();
        matrix.preScale(1,1-interpolatedTime,
                mCenterWidth,mCenterHeight);

    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(500);
        setFillAfter(true);
        mCenterWidth = width/2;
        mCenterHeight = height/2;
        setInterpolator(new AccelerateDecelerateInterpolator());
    }
}
