package com.xing.apidemo.captcha;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by zhao on 18-2-10.
 */

public abstract class CaptchaStrategy {
    protected Context mContext;

    public CaptchaStrategy(Context context){
        this.mContext = context;
    }

    /**
     * 定义缺块的形状
     * @param blockSize 单位dp，注意转化为px
     * @return path of the shape
     */
    public abstract Path getBlockShape(int blockSize);

    /**
     *  定义缺块的位置信息
     * @param width
     * @param height
     * @param blockSize
     * @return PostionInfo of the block
     */
    public abstract PositionInfo getBlockPositionInfo(int width,int height,int blockSize);

    /**
     * 定义滑块图片的位置信息（只有设置为无滑动条模式时有用）
     * @param width
     * @param height
     * @param blockSize
     * @return
     */
    public PositionInfo getPositionInfoForSwipeBlock(int width,int height,int blockSize){
        return getBlockPositionInfo(width,height,blockSize);
    }

    /**
     * 获得缺块阴影的Paint
     * @return
     */
    public abstract Paint getBlockShadowPaint();

    /**
     * 获取滑块图片的paint
     * @return
     */
    public abstract Paint getBlockBitpmapPaint();

    /**
     *
     * @param canvas
     * @param shape
     */
    public void decoreateSwipeBlockBitmap(Canvas canvas,Path shape){

    }
}
