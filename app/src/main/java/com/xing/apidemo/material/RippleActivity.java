package com.xing.apidemo.material;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.xing.apidemo.R;

public class RippleActivity extends AppCompatActivity {

    private Button mRippleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ripple);
        mRippleBtn = (Button)findViewById(R.id.btn_ripple);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            mRippleBtn.setBackgroundResource(R.attr.selectableItemBackgroundBorderless);
//            mRippleBtn.setBackground(getResources().getDrawable());
        }
    }
}
