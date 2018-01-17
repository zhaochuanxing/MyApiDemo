package com.xing.apidemo.material;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.Window;

import com.xing.apidemo.R;

/**
 * Created by zhao on 18-1-17.
 */

public class PaleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.card_history);
        Palette.Builder builder = new Palette.Builder(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                //获取色调
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                android.support.v7.app.ActionBar actionBar = getSupportActionBar();
                if(vibrant==null){
                    return;
                }
                actionBar.setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                Window window = getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(vibrant.getRgb());
                }
            }
        });
    }
}
