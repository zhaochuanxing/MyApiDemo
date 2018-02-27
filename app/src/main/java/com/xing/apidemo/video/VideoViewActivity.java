package com.xing.apidemo.video;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.xing.apidemo.R;

/**
 * 参考：http://www.cnblogs.com/plokmju/p/android_VideoView.html
 */
public class VideoViewActivity extends Activity {

    private VideoView mVideoView;
    private MediaController mControler;
    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        initView();
    }

    private void initView() {
        mVideoView = (VideoView)findViewById(R.id.video_view);
        mControler = new MediaController(this);
        mVideoView.setMediaController(mControler);
        mControler.setMediaPlayer(mVideoView);
//        mControler.setAnchorView(mVideoView);
        mControler.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoViewActivity.this,"show next",Toast.LENGTH_LONG).show();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoViewActivity.this,"show preview",Toast.LENGTH_LONG).show();
            }
        });
        mVideoView.setVideoPath(VIDEO_URL);
    }
}
