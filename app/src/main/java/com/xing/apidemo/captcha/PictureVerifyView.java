package com.xing.apidemo.captcha;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhao on 18-2-10.
 */

public class PictureVerifyView extends AppCompatImageView {

    private final DefaultCaptchaStrategy mStrategy;
    private final Paint mShadowPaint;
    private final Paint mBitmapPaint;
    private int mBlockSize;
    private PositionInfo mShadowInfo;
    private int mMode;
    private PositionInfo mBlockInfo;
    private Path mBlockShape;

    public PictureVerifyView(Context context) {
        this(context,null);
    }

    public PictureVerifyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PictureVerifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mStrategy = new DefaultCaptchaStrategy(context);
        mShadowPaint = mStrategy.getBlockShadowPaint();
        mBitmapPaint = mStrategy.getBlockBitpmapPaint();
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,mBitmapPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mShadowInfo==null){
            mShadowInfo = mStrategy.getBlockPositionInfo(getWidth(),getHeight(),mBlockSize);
            if(mMode == Captcha.MODE_BAR){
                mBlockInfo = new PositionInfo(0,mShadowInfo.top);
            }else{
                mBlockInfo = mStrategy.getPositionInfoForSwipeBlock(getWidth(),getHeight(),mBlockSize);

            }
        }
        if(mBlockShape==null){
            mBlockShape = mStrategy.getBlockShape(mBlockSize);
            mBlockShape.offset(mShadowInfo.left,mShadowInfo.top);

        }
    }
}
