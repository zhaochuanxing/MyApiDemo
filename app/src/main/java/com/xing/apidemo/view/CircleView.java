package com.xing.apidemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.xing.apidemo.R;
import com.xing.apidemo.Util.SizeUtil;

/**
 * Created by zhao on 17-10-3.
 */

public class CircleView extends View {

    private int mColor  = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mSize;

    public CircleView(Context context) {
        super(context);
        init(null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleViewAttr);
        mColor = typedArray.getColor(R.styleable.CircleViewAttr_circle_color,Color.BLUE);
        typedArray.recycle();
        mPaint.setColor(mColor);
        mSize = SizeUtil.dip2px(getContext(),200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getWidth()-paddingLeft - paddingRight;
        int height = getHeight()-paddingBottom - paddingTop;
        int radius = Math.min(width,height)/2;
        canvas.drawCircle(paddingLeft + width/2,paddingTop + height/2,radius,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heighMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heighMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mSize,mSize);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mSize,heightSize);
        }else if(heighMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,mSize);
        }
    }


}
