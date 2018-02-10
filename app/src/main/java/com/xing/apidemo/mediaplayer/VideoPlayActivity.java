package com.xing.apidemo.mediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.xing.apidemo.R;

import java.io.IOException;

/*
 * 参考：https://www.cnblogs.com/plokmju/p/android_SurfaceView.html
 */
public class VideoPlayActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private String TAG = VideoPlayActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initView();
//        mSurfaceView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                initPlayer();
//            }
//        },2000);
    }

    private void initPlayer() {
        String videoUrl = "http://flv3.bn.netease.com/videolib3/1801/10/fLcmb0099/SD/fLcmb0099-mobile.mp4";
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.i(TAG,"onPrepared ");
                    mp.start();
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.i(TAG,"onCompletion ");

                }
            });

            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.i(TAG,"onError "+what+","+extra);
                    return false;
                }
            });

            mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.i(TAG,"onBufferingUpdate "+percent);
                }
            });

            mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                @Override
                public void onSeekComplete(MediaPlayer mp) {
                    Log.i(TAG,"onSeekComplete ");
                }
            });
            mMediaPlayer.setDataSource(videoUrl);
            //将mediaplayer 与surfaceholder关联起来，mediaplayer将图像数据传递给surface
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            //异步加载
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mSurfaceView = (SurfaceView)findViewById(R.id.surface_view);
        mSurfaceView.getHolder().addCallback(mCallBack);
    }

    private int mLastPlayPos;
    private SurfaceHolder.Callback mCallBack = new SurfaceHolder.Callback(){

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i(TAG,"surfaceCreated "+mLastPlayPos);
//            if(mLastPlayPos>0){
//                mMediaPlayer.start();
//                mMediaPlayer.seekTo(mLastPlayPos);
//                mLastPlayPos = 0;
//            }else{
//                initPlayer();
//            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.i(TAG,"surfaceChanged "+holder);

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.i(TAG,"surfaceDestroyed");
            if(mMediaPlayer.isPlaying()){
                mLastPlayPos = mMediaPlayer.getCurrentPosition();
                mMediaPlayer.stop();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer!=null){
            mMediaPlayer.release();
        }
    }
}
