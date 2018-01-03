package com.xing.apidemo.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.xing.apidemo.R;

import java.io.StreamTokenizer;
import java.util.HashMap;

/**
 * Created by zhao on 18-1-3.
 */

public class SoundPoolUtil {

    private static final int KEY_RING = 1;
    private static final int KEY_REJECT = 2;
    private static volatile SoundPoolUtil sSoundPoolUtil;
    private final Context mContext;
    private final SoundPool mSoundPool;
    private final HashMap<Integer, Integer> mSoundMap;
    private int mCurrentId;

    private SoundPoolUtil(Context context) {
        mContext = context.getApplicationContext();
        mSoundPool = new SoundPool(2, AudioManager.STREAM_RING,0);
        mSoundMap = new HashMap<Integer,Integer>();
        mSoundMap.put(KEY_RING,mSoundPool.load(mContext, R.raw.ring,1));
        mSoundMap.put(KEY_REJECT,mSoundPool.load(mContext,R.raw.reject,1));

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.i("sound","soundPool "+sampleId+",status "+status);
            }
        });
    }

    public static SoundPoolUtil getInstance(Context context) {
        if (sSoundPoolUtil == null) {
            synchronized (SoundPoolUtil.class) {
                if (sSoundPoolUtil == null) {
                    sSoundPoolUtil = new SoundPoolUtil(context);
                }
            }
        }
        return sSoundPoolUtil;
    }

    public void playRing() {
        stopPlay();
        mCurrentId =  mSoundPool.play(mSoundMap.get(KEY_RING), 1.0f, 1.0f, 1, -1, 1);
    }

    public void playReject(){
        stopPlay();
        mCurrentId = mSoundPool.play(mSoundMap.get(KEY_REJECT),1.0f,1.0f,1,0,1);
    }

    public void stopPlay() {
        mSoundPool.autoPause();
        if (mCurrentId != 0) {
            mSoundPool.stop(mCurrentId);
            mCurrentId = 0;
        }
    }

    private void release(){
        mSoundPool.release();
    }
}
