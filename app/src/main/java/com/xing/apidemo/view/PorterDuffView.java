package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xing.apidemo.R;

/**
 * Created by zhao on 18-1-1.
 */

public class PorterDuffView extends BaseView {
    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public PorterDuffView(Context context) {
        super(context);
    }

    public PorterDuffView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        mOut = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

    }

    public PorterDuffView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

////        Canvas newCanvas = new Canvas(mOut);
//        canvas.drawBitmap(mOut,0,0,mPaint);
////        canvas.drawRect(0,0,mBitmap.getWidth()-100,mBitmap.getHeight()-100,mPaint);
//        canvas.drawCircle(mBitmap.getWidth()/2,mBitmap.getWidth()/2,mBitmap.getWidth()/2,mPaint);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(mBitmap,0,0,mPaint);

//        int min = Math.min(mWidth,mHeight);
//        Bitmap srcBitmap = Bitmap.createScaledBitmap(mBitmap,min,min,false);
//        Bitmap circleBitmap = createCircleBitmap(srcBitmap, min);
//        canvas.drawBitmap(circleBitmap,0,0,null);
        Bitmap roundBitmap = createRoundBitmap(mBitmap);
        canvas.drawBitmap(roundBitmap,0,0,null);
    }

    /**
     * 添加圆角
     * @param source
     * @return
     */
    private Bitmap createRoundBitmap(Bitmap source){
        final Paint paint = new Paint();
        paint.setAntiAlias(false);
        Bitmap target = Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_8888);
        //给画布上放一张空白的纸，来进行绘画，画成一幅画
        Canvas canvas = new Canvas(target);
        //切割一个圆角矩形
        RectF rectF = new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        canvas.drawRoundRect(rectF,80,80,paint);
        //设置画笔的绘制方式，对下一次的绘制有效
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source,0,0,paint);
        //画布的画纸上留下了一幅新的bitmap图
        return target;
    }

    /**
     * 切割圆形
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleBitmap(Bitmap source,int min){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        //创建一个对应大小的方形空的 可以修改的bitmap
        Bitmap target = Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        //创建一个同样大小的画布
        Canvas canvas = new Canvas(target);
        //首先绘制圆形
        canvas.drawCircle(min/2,min/2,min/2,paint);

        //设置src_in
        //SRC_IN这种模式，两个绘制的效果叠加后取交集展现后图，怎么说呢，咱们第一个绘制的是个圆形，第二个绘制的是个Bitmap，
        // 于是交集为圆形，展现的是BItmap，就实现了圆形图片效果
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //绘制图片
        canvas.drawBitmap(source,0,0,paint);
        //绘制完成后，将会得到一个新的bitmap
        return target;
    }
}
