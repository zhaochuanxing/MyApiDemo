package com.xing.apidemo.newview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.xing.apidemo.R;

public class ViewActivity extends AppCompatActivity {

    private static final String TAG = ViewActivity.class.getSimpleName();
    private CircleView mCircleView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view2);
        mCircleView = (CircleView)findViewById(R.id.circle_view);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG,"top: "+mCircleView.getTop()+","+mCircleView.getLeft()+","+mCircleView.getRight()+","+mCircleView.getBottom());
                Log.i(TAG,"width "+mCircleView.getWidth()+","+mCircleView.getHeight());
            }
        },200);
    }
}
