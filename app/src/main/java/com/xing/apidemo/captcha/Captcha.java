package com.xing.apidemo.captcha;

import android.support.annotation.IntDef;

/**
 * Created by zhao on 18-2-10.
 */

public class Captcha {

    public static final int MODE_BAR = 1;
    public static final int MODE_NO_BAR = 2;

    @IntDef(value={MODE_BAR,MODE_NO_BAR})
    public @interface Mode{

    }
}
