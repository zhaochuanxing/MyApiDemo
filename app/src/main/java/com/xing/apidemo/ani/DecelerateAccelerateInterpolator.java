package com.xing.apidemo.ani;

import android.animation.TimeInterpolator;

/**
 * Created by zhao on 18-1-16.
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {

    private static final float mMiddle = 0.5f;

    /**
     * http://blog.csdn.net/guolin_blog/article/details/44171115
     *
     * 这段代码是使用正弦函数来实现先减速后加速的功能的，因为正弦函数初始弧度的变化值非常大，刚好和余弦函数是相反的，而随着弧度的增加，正弦函数的变化值也会逐渐变小，这样也就实现了减速的效果。当弧度大于π/2之后，整个过程相反了过来，现在正弦函数的弧度变化值非常小，渐渐随着弧度继续增加，变化值越来越大，弧度到π时结束，这样从0过度到π，也就实现了先减速后加速的效果。
     * @param input
     * @return
     */
    @Override
    public float getInterpolation(float input) {
        float result = 0;
        if(input <=mMiddle){
            result = (float)(Math.sin(Math.PI*input))/2;
        }else{
            result = (float)(2-Math.sin(Math.PI*input))/2;
        }
        return result;
    }
}
