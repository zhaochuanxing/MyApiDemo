package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xing.apidemo.R;

/**
 * Created by zhao on 18-1-1.
 */

public class SwipView extends BaseView {

    private Paint mPaint;
    private Path mPath;
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Canvas mCanvas;

    public SwipView(Context context) {
        super(context);
    }

    public SwipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public SwipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(50);
        //在连接处可以更圆滑一点
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        mFgBitmap = Bitmap.createBitmap(mBgBitmap.getWidth(),mBgBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //Construct a mCanvas with the specified bitmap to draw into. The bitmap
//     * must be mutable.
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制底图
        canvas.drawBitmap(mBgBitmap,0,0,null);
        //绘制前景图
        //在触摸事件的处理中，会将前景图bitmap做修改，并触发重绘
        canvas.drawBitmap(mFgBitmap,0,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;

        }
        mCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }


}
