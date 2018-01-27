package com.xing.apidemo.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

public class ScrollActivity extends AppCompatActivity {

    private Button mScrllBtn;
    private MyScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mScrollView = (MyScrollView)findViewById(R.id.scroll_view);
        mScrllBtn = (Button)findViewById(R.id.btn_scroll);
        mScrllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  mScrollView.smoothScrollBy(-40,-40);
//                    mScrollView.smoothScrollTo(100,100);
            }
        });
    }
}
