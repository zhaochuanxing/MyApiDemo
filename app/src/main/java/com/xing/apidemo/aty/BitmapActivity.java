package com.xing.apidemo.aty;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.xing.apidemo.R;

public class BitmapActivity extends Activity {

    private static final String TAG = BitmapActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        ImageView originImg = (ImageView)findViewById(R.id.img_origin);
        ImageView newImg = (ImageView)findViewById(R.id.img_new);

        Drawable drawable = this.getResources().getDrawable(R.drawable.fish);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        originImg.setImageDrawable(bitmapDrawable);

        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap newBitmap = handleImage(bitmap);
        newImg.setImageBitmap(newBitmap);
    }

    private Bitmap handleImage(Bitmap bm) {
        if (bm == null) {
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0;
        int r, g, b, a;
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        Log.i(TAG, "oldPx = " + oldPx);
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            //采用位运算获取色值中的 红色值 移位 与运算
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);
            r = 255 - r;
            g = 255-g;
            b = 255 - b;
            //采用位运算 移位和或运算 组合成颜色值
            newPx[i] = Color.argb(a,r,g,b);
        }
        bmp.setPixels(newPx,0,width,0,0,width,height);
        return bmp;
    }
}
