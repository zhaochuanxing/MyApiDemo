package com.xing.apidemo.myviewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhao on 17-10-21.
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            MarginLayoutParams childParam = (MarginLayoutParams) childView.getLayoutParams();

            int childLeft =0,childTop = 0,childRight = 0, childBottom =0;

            if(i==0){
                childLeft = childParam.leftMargin;
                childTop = childParam.topMargin;
            }else if(i==1){
                childLeft = getWidth()-childWidth - childParam.rightMargin;
                childTop = childParam.topMargin;
            }else if(i==2){
                childLeft = childParam.leftMargin;
                childTop = getHeight() - childHeight - childParam.bottomMargin;
            }else if (i==3){
                childLeft = getWidth() -childWidth -childParam.rightMargin;
                childTop = getHeight() - childHeight - childParam.bottomMargin;
            }

            childRight = childLeft + childWidth;
            childBottom = childTop + childHeight;
            childView.layout(childLeft,childTop,childRight,childBottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int childCount = getChildCount();
        int childWidth = 0;
        int childHeight = 0;
        MarginLayoutParams childParam = null;

        int leftHeight = 0;
        int rightHeight = 0;

        int topWidth = 0;
        int bottomwidth = 0;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            childParam = (MarginLayoutParams) childView.getLayoutParams();

            if (i == 0 || i == 1) {
                topWidth += childWidth + childParam.leftMargin + childParam.rightMargin;
            }

            if (i == 2 || i == 3) {
                bottomwidth += childWidth + childParam.leftMargin + childParam.rightMargin;
            }

            if (i == 0 || i == 2) {
                leftHeight += childHeight + childParam.topMargin + childParam.bottomMargin;
            }

            if (i == 1 || i == 3) {
                rightHeight += childHeight + childParam.topMargin + childParam.bottomMargin;
            }

            width = Math.max(topWidth, bottomwidth);
            height = Math.max(leftHeight, rightHeight);
            setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);

        }
    }
}
