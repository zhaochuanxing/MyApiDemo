package com.xing.apidemo.captcha;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;

import com.xing.apidemo.Util.SizeUtil;

/**
 * Created by zhao on 18-2-10.
 */

public class TextSeekBar extends AppCompatSeekBar {

    private Paint mTextPaint;

    public TextSeekBar(Context context) {
        this(context,null);
    }

    public TextSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextPaint = new Paint();
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        int textSize = SizeUtil.dip2px(context,14);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.parseColor("#545454"));
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        //为基线到字体上边框的距离,即上图中的top
        float top = fontMetrics.top;
        //为基线到字体下边框的距离,即上图中的bottom
        float bottom = fontMetrics.bottom;
        //基线中间点的y轴计算公式
        int baseLineY = (int)(getHeight()/2-top/2-bottom/2);
        canvas.drawText("向右移动滑块完成拼图",getWidth()/2,baseLineY,mTextPaint);

    }
}
