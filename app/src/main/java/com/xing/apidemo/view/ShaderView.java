package com.xing.apidemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xing.apidemo.R;

/**
 * Created by zhao on 18-1-1.
 */

public class ShaderView extends BaseView {
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint;
    private Paint mRepeatPaint;
    private Paint mGradientPaint;

    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        initRepeat();
        initGradient();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setShader(mBitmapShader);
    }

    private void initRepeat(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mRepeatPaint = new Paint();
        mRepeatPaint.setShader(bitmapShader);
    }

    private void initGradient(){
        mGradientPaint = new Paint();
        mGradientPaint.setShader(new LinearGradient(0,0,400,400, Color.BLUE,Color.YELLOW, Shader.TileMode.REPEAT));

    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawcirle 等必须要有paint
        // drawbitmap 则paint可以为空
        canvas.drawCircle(500,500,200,mPaint);
        canvas.drawCircle(500,800,200,mRepeatPaint);
        canvas.drawRect(0,700,800,800+700,mGradientPaint);
    }
}
