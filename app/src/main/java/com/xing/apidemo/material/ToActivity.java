package com.xing.apidemo.material;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;

import com.xing.apidemo.R;

public class ToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        int flag = -1;
        if(getIntent()!=null){
            Bundle extras = getIntent().getExtras();
            if(extras!=null){
                flag = extras.getInt(FromActivity.KEY_FLAG);
            }
        }
        switch (flag){
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setEnterTransition(new Explode());
                    getWindow().setExitTransition(new Explode());
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setEnterTransition(new Slide());
                    getWindow().setExitTransition(new Slide());
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setEnterTransition(new Fade());
                    getWindow().setExitTransition(new Fade());
                }
                break;
            default:
                break;

        }
        setContentView(R.layout.activity_to);

    }
}
