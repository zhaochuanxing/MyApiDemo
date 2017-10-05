package com.xing.apidemo.Util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by zhao on 17-10-3.
 */

public class Density {

    public static int dip2px(Context context,int dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*density+0.5f);
    }

    public static int px2dip(Context context,int pxValue){
       float density =  context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/density + 0.5f);
    }

    public static int dp2px(int dpVal,Context context)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}
