package com.xing.apidemo.material;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import com.xing.apidemo.R;

public class ClipActivity extends AppCompatActivity {

    private TextView mRoundTx;
    private TextView mCircleTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
        mRoundTx = (TextView) findViewById(R.id.tx_round);
        mCircleTx = (TextView) findViewById(R.id.tx_circle);
        mRoundTx.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        initView();

    }

    //    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    @SuppressLint("NewApi")
//仅仅是将lint的警告去掉了，运行时还是会报出错误来的
    private void initView() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ViewOutlineProvider viewOutlineProvider = null;
            Log.i("zcx","init");
            viewOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30);
                        Log.i("zcx","outline = "+outline);
                    }
                }
            };
            mRoundTx.setOutlineProvider(viewOutlineProvider);

            ViewOutlineProvider circleOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        outline.setOval(0,0,view.getWidth(),view.getHeight());
                    }
                }
            };
            mCircleTx.setOutlineProvider(circleOutlineProvider);
        }

    }
}
