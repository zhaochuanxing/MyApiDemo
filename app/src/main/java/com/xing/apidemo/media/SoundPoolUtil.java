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
    private static final String TAG = "sound";
    private static volatile SoundPoolUtil sSoundPoolUtil;
    private final Context mContext;
    private final SoundPool mSoundPool;
    private final HashMap<Integer, Integer> mSoundMap;
    private int mCurrentId;
    private boolean mIsLoaded;

    private SoundPoolUtil(Context context) {
        mContext = context.getApplicationContext();
        mSoundPool = new SoundPool(2, AudioManager.STREAM_RING,0);
        mSoundMap = new HashMap<Integer,Integer>();


        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.i("sound","soundPool "+sampleId+",status "+status);
                mIsLoaded = true;
            }
        });

        mSoundMap.put(KEY_RING,mSoundPool.load(mContext, R.raw.ring,1));
        mSoundMap.put(KEY_REJECT,mSoundPool.load(mContext,R.raw.reject,1));
        Log.i(TAG,"sound map = "+mSoundMap);
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
        if(!mIsLoaded){
            mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    if(sampleId == mSoundMap.get(KEY_RING)){
                        playSoundId(sampleId,true);
                    }
                    Log.i(TAG,"playRing setOnLoadCompleteListener "+sampleId);
                    mIsLoaded = true;
                }
            });
        }else{
            playSoundId(mSoundMap.get(KEY_RING),true);
        }
        Log.i(TAG,"playRing "+mCurrentId);
    }

    private int playSoundId(int sampleId,boolean isLoop) {
         mCurrentId = mSoundPool.play(sampleId, 1f, 1f, 1, isLoop ? -1 : 0, 1);
        Log.i(TAG,"mCurrentId "+mCurrentId);
        return mCurrentId;
    }

    public void playReject(){
        stopPlay();
        if(!mIsLoaded){
            mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    if(sampleId == mSoundMap.get(KEY_REJECT)){
                        playSoundId(sampleId,false);
                    }
                    Log.i(TAG,"playRing setOnLoadCompleteListener "+sampleId);
                    mIsLoaded = true;
                }
            });
        }else{
            playSoundId(mSoundMap.get(KEY_REJECT),false);
        }
        Log.i(TAG,"playReject "+mCurrentId );
    }

    public void stopPlay() {
//        mSoundPool.autoPause();
        Log.i(TAG,"sound map = "+mSoundMap);

        Log.i(TAG,"stopPlay "+mCurrentId);
        if (mCurrentId != 0) {
            mSoundPool.stop(mCurrentId);
        }
    }

    private void release(){
        mSoundPool.release();
    }
}
