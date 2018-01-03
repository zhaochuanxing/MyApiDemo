package com.xing.apidemo.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

/**
 * Created by zhao on 18-1-2.
 */

public class SoundPoolHelper {

    public final static int TYPE_MUSIC= AudioManager.STREAM_MUSIC;
    public final static int TYPE_ALAUM = AudioManager.STREAM_ALARM;
    public final static int TYPE_RING = AudioManager.STREAM_RING;
    private final SoundPool mSoundPool;
    private final HashMap<Integer, Integer> mSoundMap;
    private final int mMaxStream;

    @IntDef({TYPE_MUSIC,TYPE_ALAUM,TYPE_RING})
    @Retention(RetentionPolicy.CLASS)
    public @interface Type{}

    public SoundPoolHelper(int maxStream,@Type int streamType){
        mSoundPool = new SoundPool(maxStream,streamType,1);
        mSoundMap = new HashMap<Integer,Integer>();
        this.mMaxStream = maxStream;
    }


}
