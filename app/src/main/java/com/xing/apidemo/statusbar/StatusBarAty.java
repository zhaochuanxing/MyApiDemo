package com.xing.apidemo.statusbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.xing.apidemo.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StatusBarAty extends AppCompatActivity {

    private static final String TAG = StatusBarAty.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_aty);
        setStatusColor();
    }

    private void setStatusColor() {
        int color = Color.WHITE;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            Window window = this.getWindow();
            window.setStatusBarColor(color);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        }

        String manufacturer = Build.MANUFACTURER;
        Log.i(TAG,"brand: "+manufacturer);
        if("Xiaomi".equals(manufacturer)){
            setStatusBarDarkMode(true,this);
        }
    }

    public void setStatusBarDarkMode(boolean darkMode,Activity activity){
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try{
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags",int.class,int.class);
            extraFlagField.invoke(activity.getWindow(),darkMode?darkModeFlag:0,darkModeFlag);
            Log.d(TAG ,"set Success");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
