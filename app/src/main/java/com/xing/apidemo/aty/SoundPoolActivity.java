package com.xing.apidemo.aty;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xing.apidemo.R;
import com.xing.apidemo.media.MediaSoundUtil;
import com.xing.apidemo.media.SoundPoolUtil;

import java.util.HashMap;

public class SoundPoolActivity extends Activity implements View.OnClickListener {

    private SoundPool mSoundPool;
    private HashMap<Integer, Integer> mSoundMap = new HashMap<>();
    private Button mBtnOne;
    private Button mBtnTwo;
    private Button mBtnThree;
    private SoundPoolActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);
        mActivity = this;
        mSoundPool = new SoundPool(2, AudioManager.STREAM_RING, 5);
        mSoundMap.put(1, mSoundPool.load(this, R.raw.ring, 1));
        mSoundMap.put(2, mSoundPool.load(this, R.raw.reject, 1));

        mBtnOne = (Button) findViewById(R.id.button1);
        mBtnTwo = (Button) findViewById(R.id.button2);
        mBtnThree = (Button) findViewById(R.id.button3);
        mBtnOne.setOnClickListener(this);
        mBtnTwo.setOnClickListener(this);
        mBtnThree.setOnClickListener(this);
//        SoundPoolUtil.getInstance(mActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
//                SoundPoolUtil.getInstance(mActivity).playRing();
                MediaSoundUtil.getInstance(mActivity).playRingSound();
                break;
            case R.id.button2:
//                SoundPoolUtil.getInstance(mActivity).playReject();
                MediaSoundUtil.getInstance(mActivity).playRejectSound();
                break;
            case R.id.button3:
//                SoundPoolUtil.getInstance(mActivity).stopPlay();
                MediaSoundUtil.getInstance(mActivity).stopPlay();
                break;
            default:
                break;
        }
    }
}
