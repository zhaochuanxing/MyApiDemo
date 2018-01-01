package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by zhao on 18-1-1.
 */

public class LayerView extends BaseView {
    private static final String TAG = LayerView.class.getSimpleName();
    private Paint mPaint;
    private int mAlpha;
    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG|Canvas.CLIP_SAVE_FLAG
            |Canvas.HAS_ALPHA_LAYER_SAVE_FLAG|Canvas.FULL_COLOR_LAYER_SAVE_FLAG
            |Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    public LayerView(Context context) {
        super(context);
        Log.i(TAG,"LayerView one");
    }

    public LayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 在xml里面引用的view,会调用到这个方法。
        Log.i(TAG,"two");
        init();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG,"three");
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150,150,100,mPaint);

        //本身和save方法差不多，但是它单独分配了一个画布用于绘制图层。
        // 它定义了一个画布区域（可设置透明度），此方法之后的所有绘制都在此区域中绘制，直到调用canvas.restore()方法。
        canvas.saveLayerAlpha(0,0,400,400,mAlpha,LAYER_FLAGS);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200,200,100,mPaint);
        canvas.restore();
    }

    private void init() {
        mPaint = new Paint();
        mAlpha = 127;

    }
}
