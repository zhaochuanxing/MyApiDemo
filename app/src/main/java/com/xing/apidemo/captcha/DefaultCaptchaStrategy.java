package com.xing.apidemo.captcha;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by zhao on 18-2-10.
 */

public class DefaultCaptchaStrategy extends CaptchaStrategy {

    public DefaultCaptchaStrategy(Context context) {
        super(context);
    }

    @Override
    public Path getBlockShape(int blockSize) {
        return null;
    }

    @Override
    public PositionInfo getBlockPositionInfo(int width, int height, int blockSize) {
        return null;
    }

    @Override
    public Paint getBlockShadowPaint() {
        return null;
    }

    @Override
    public Paint getBlockBitpmapPaint() {
        return null;
    }
}
