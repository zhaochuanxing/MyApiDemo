package com.xing.apidemo.ani;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zhao on 18-1-17.
 */

public class RotateAnimation extends Animation {
    //开始角度
    private final float mFromDegrees;
    //结束角度
    private final float mToDegrees;
    //中心点
    private  float mCenterX;
    private  float mCenterY;
    private final float mDepthZ;
    //是否需要扭曲
    private final boolean mReverse;
    //摄像头
    private Camera mCamera;

    public RotateAnimation(float fromDegrees, float toDegrees
                            , float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;

        mDepthZ = depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        mCenterX = width/2;
        mCenterY = height/2;
        setDuration(2000);
    }
    //生成Transformation
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        //生成中间角度
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);

        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;

        final Matrix matrix = t.getMatrix();

        camera.save();
        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);
        //取得变换后的矩阵
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}