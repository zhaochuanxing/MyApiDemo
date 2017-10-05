package com.xing.apidemo.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by zhao on 17-10-3.
 */

public class RoundImageDrawable extends Drawable {

    private final Bitmap mBitmap;
    private final Paint mPaint;
    private final boolean mIsCirlce;
    private  int mWidth;
    private RectF mRectF;

    public RoundImageDrawable(Bitmap bitmap,boolean isCircle){
        this.mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(bitmapShader);
        this.mIsCirlce = isCircle;
        if(isCircle){
            mWidth = Math.min(bitmap.getWidth(),bitmap.getHeight());
        }
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRectF = new RectF(left,top,right,bottom);
    }

    @Override
    public void setBounds(Rect bounds) {
        super.setBounds(bounds);
    }

    @Override
    public void draw(Canvas canvas) {
        if(mIsCirlce){
            canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2,mPaint);
        }else{
            canvas.drawRoundRect(mRectF,30,30,mPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    public int getIntrinsicHeight(){
        return mBitmap.getHeight();
    }


}
