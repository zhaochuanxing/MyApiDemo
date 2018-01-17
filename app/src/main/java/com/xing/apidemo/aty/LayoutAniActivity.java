package com.xing.apidemo.aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xing.apidemo.R;

public class LayoutAniActivity extends Activity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_ani);
        initView();
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
        scaleAnimation.setDuration(2000);
        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(scaleAnimation,0.5f);
        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_REVERSE);
        linearLayout.setLayoutAnimation(layoutAnimationController);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            addBtn();
        }
        return super.onTouchEvent(event);
    }

    private void addBtn() {
        Button button = new Button(this);
        button.setText("button");
        linearLayout.addView(button,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }


}
