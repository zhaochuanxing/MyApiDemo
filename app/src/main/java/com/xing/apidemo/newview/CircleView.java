package com.xing.apidemo.newview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.xing.apidemo.R;

public class CircleView extends View{

    private final int mColor;
    private Paint mPaint;


    public CircleView(Context context) {
        this(context,null);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载自定义属性集合
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        //解析集合中的属性
        //属性id为R.styleable_CircleView_circle_new_color,采用复合的方式可以实现id是唯一的
        mColor = typedArray.getColor(R.styleable.CircleView_circle_new_color,Color.RED);
        typedArray.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取宽的测量规则的模式和大小
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        // 获取高的测量规则模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //　设置默认的宽高
        int width = 400;
        int height = 400;
        int layoutWidth = getLayoutParams().width;
        int layoutHeight = getLayoutParams().height;
        if(layoutWidth == ViewGroup.LayoutParams.WRAP_CONTENT && layoutHeight== ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(width,height);
        }else if(layoutWidth == ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(width,heightSize);
        }else if(layoutHeight == ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(withSize,height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //padding 是属于view的自己事情，padding 也是属于view的绘制范围之内了，在真正的内容区之间，增加间隙
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        //获取真正绘制圆形的宽高
        int width = getWidth()-paddingLeft-paddingRight;
        int height = getHeight()-paddingTop-paddingBottom;
        int radius = Math.min(width,height)/2;
        //圆形的位置：从图中可以直观的看到：paddingLeft+width/2同理y轴
        canvas.drawCircle(paddingLeft+width/2,paddingTop+height/2,radius,mPaint);
        
    }
}
