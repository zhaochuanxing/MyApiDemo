package com.xing.apidemo.material;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

public class ZorderActivity extends AppCompatActivity {

    private Button mBtn;
    private boolean mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zorder);
        initView();
    }

    private void initView() {
        mBtn = (Button)findViewById(R.id.button1);
        ViewCompat.setElevation(mBtn,20);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFlag){
                    ViewCompat.animate(mBtn).translationZ(50);

                }else{
                    ViewCompat.animate(mBtn).translationZ(20);
                }
                mFlag = !mFlag;
            }
        });

    }
}
