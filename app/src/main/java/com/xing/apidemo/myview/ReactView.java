package com.xing.apidemo.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Size;
import android.view.View;

import com.xing.apidemo.R;
import com.xing.apidemo.view.BaseView;

/**
 * Created by zhao on 18-1-28.
 */

public class ReactView extends BaseView {

    private static final int SIZE_DEFAULT = 400;
    private final Context mContext;
    private Paint mPaint;
    private int mColor;

    public ReactView(Context context) {
        this(context, null);
    }

    public ReactView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ReactView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initDraw(attrs);
    }

    private void initDraw(AttributeSet attrs) {
        if (attrs != null) {
            //从attrs文件中找到 名字对应的declare-styleable，提供了解析用的信息
            //attrs是从布局文件中获取到的属性的赋值的封装类
            TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.RectView);
            //根据属性名称提取属性值，因为会有多个属性，因此这步是必要的
            //注意这个参数的名称，是属性组的整体的名称_属性名称，通过这种方式来找到真正的属性值，后面提供的时默认值
            //可能会觉得这个多此一举，因为typedArray本来就已经通过属性组的整体名称来找到的，是包含这个信息
            //但是这里引用的是编译时的R id值，这个需要在编译时就要保证唯一性，因此这里采用了组合的方式确定id
            mColor = typedArray.getColor(R.styleable.RectView_rect_color,Color.RED);
            //使用完需要回收
            //注意：不要回收两次否则会报出异常;调用回收之后不能再去引用使用了
            //这个是为了复用，这个类涉及View属性的解析，因此使用应该是非常频繁的
            typedArray.recycle();
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //获取绘制的真正的宽度，这个是整体宽度- left和right
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        canvas.drawRect(0 + paddingLeft, 0 + paddingTop, width + paddingLeft, height + paddingTop, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //需要对wrap_content这种情况做尺寸的处理，除了具体的数值 match_parent这些，父容器
        //可以准确知道尺寸的大小，但是wrap_content需要view自己去确定，因此可以在这里指定
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(SIZE_DEFAULT, SIZE_DEFAULT);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(SIZE_DEFAULT, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, SIZE_DEFAULT);
        }
    }
}
