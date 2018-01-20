package com.xing.apidemo.material;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;

public class FromActivity extends AppCompatActivity {

    public static final String KEY_FLAG = "KEY_FLAG";
    private Intent mIntent;
    private Button mExplodeBtn;
    private Button mSlideBtn;
    private Button mFadeBtn;
    private Button mNormalBtn;
    private Button mFabBtn;
    private Button mShareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);
        mExplodeBtn = (Button)findViewById(R.id.btn_explode);
        mSlideBtn = (Button)findViewById(R.id.btn_slide);
        mFadeBtn = (Button)findViewById(R.id.btn_fade);
        mNormalBtn = (Button)findViewById(R.id.btn_normal);
        mFabBtn = (Button)findViewById(R.id.btn_fab);
        mShareBtn = (Button)findViewById(R.id.btn_share);

        mFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });

        mNormalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal();
            }
        });

        mFadeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fade();
            }
        });

        mExplodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explode();
            }
        });

        mSlideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide();
            }
        });
    }

    private void explode(){
        mIntent = new Intent(this,ToActivity.class);
        mIntent.putExtra(KEY_FLAG,0);
        startActivity(mIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    private void slide(){
        mIntent = new Intent(this,ToActivity.class);
        mIntent.putExtra(KEY_FLAG,1);
        startActivity(mIntent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    private void fade(){
        mIntent = new Intent(this,ToActivity.class);
        mIntent.putExtra(KEY_FLAG,2);
        startActivity(mIntent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    private void normal(){
        mIntent = new Intent(this,ToActivity.class);
        startActivity(mIntent);
    }

    private void share(){
        mIntent = new Intent(this,ToActivity.class);
        mIntent.putExtra(KEY_FLAG,3);
//        startActivity(mIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(this,mFabBtn,"fab").toBundle());
        startActivity(mIntent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,new android.support.v4.util.Pair<View, String>(mShareBtn,"share"),new android.support.v4.util.Pair<View, String>(mFabBtn,"fab")).toBundle());
    }
}
