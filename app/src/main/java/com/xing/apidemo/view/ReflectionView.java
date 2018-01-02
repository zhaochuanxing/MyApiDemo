package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xing.apidemo.R;

/**
 * Created by zhao on 18-1-2.
 */

public class ReflectionView extends BaseView {

    private Bitmap mSrcBitmap;
    private Bitmap mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXferMode;

    public ReflectionView(Context context) {
        super(context);
    }

    public ReflectionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.card_history);
        Matrix martrix = new Matrix();
        martrix.setScale(1F,-1F);
        //将原图进行复制，并进行翻转
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap,0,0,mSrcBitmap.getWidth(),mSrcBitmap.getHeight(),martrix,true);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0,mSrcBitmap.getHeight(),
                0,mSrcBitmap.getHeight()+mSrcBitmap.getHeight(),
                0xdd000000,0x10000000, Shader.TileMode.CLAMP));
        mXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    public ReflectionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(mSrcBitmap,0,0,null);
        canvas.drawBitmap(mRefBitmap,0,mSrcBitmap.getHeight(),null);
        mPaint.setXfermode(mXferMode);
        canvas.drawRect(0,mSrcBitmap.getHeight(),mRefBitmap.getWidth(),mRefBitmap.getHeight()*2,mPaint);
        mPaint.setXfermode(null);
    }
}
