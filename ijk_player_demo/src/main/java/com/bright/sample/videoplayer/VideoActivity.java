/**
 * Copyright (C) 2016 The yuhaiyang Android Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author: y.haiyang@qq.com
 */

package com.bright.sample.videoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bright.sample.videoplayer.constant.Configure;
import com.bright.videoplayer.utils.ScreenOrientationUtils;
import com.bright.videoplayer.widget.MediaController;
import com.bright.videoplayer.widget.media.VideoView;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoActivity extends AppCompatActivity {
    private static final String TAG = "VideoActivity";
    private MediaController mMediaController;
    private VideoView mVideoView;
    //    final private String url = "http://bj.bcebos.com/v1/wylyunying/2cf13b84-695e-4f63-b63e-dd3ad48bec45/film/ed298d4d-e215-4518-a57b-17debb912b74.mp4?s=28054127";
//    final private String url = "http://mss.pinet.co/index.php/api/retrieve/3da4edce-b445-42c8-88a7-3b8a1997d61c/playlist.m3u8";
    private String url = "http://192.168.1.199:8080/Mynf/image/get?url=108.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // init UI
        mMediaController = new MediaController(this);
        mMediaController.setCallBack(mCallBack);
        mMediaController.setTitle("变形金刚2");

        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setMediaController(mMediaController);
        // prefer mVideoPath
//        mVideoView.setVideoPath("http://mss.pinet.co/index.php/api/retrieve/3da4edce-b445-42c8-88a7-3b8a1997d61c/playlist.m3u8");
        mVideoView.setVideoPath(url);

        mVideoView.start();


        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {


            }
        });

        mVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {


                Log.e(TAG, "onInfo: extra=" + extra);
                Log.e(TAG, "onInfo: what=" + what);
                return false;
            }
        });


//        mVideoView.setShowAction();;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Log.e(TAG, "run:getBufferPercentage= " + mVideoView.getBufferPercentage());
                Log.e(TAG, "run: getCurrentPosition=" + mVideoView.getCurrentPosition());
                Log.e(TAG, "run:getDuration= " + mVideoView.getDuration());


            }
        }, 2000);
        createAd();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!Configure.SHOW_BAIDU_AD) {
            Log.i(TAG, "onResume: Configure.SHOW_BAIDU_AD is false");
            return;
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: postDelayed");
            }
        }, 500);
    }

    private Handler mHandler = new Handler() {
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (mVideoView.canPause()) {
            mVideoView.pause();
        }
    }

    @Override
    public void onBackPressed() {
        if (ScreenOrientationUtils.isLandscape(this)) {
            mMediaController.changePortrait(false);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mVideoView.stopPlayback();
        mVideoView.release(true);
        IjkMediaPlayer.native_profileEnd();
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 切换到横屏
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mMediaController.changeLand(true);
        } else {
            mMediaController.changePortrait(true);
        }
    }


    private MediaController.CallBack mCallBack = new MediaController.CallBack() {
        @Override
        public void onPlay(boolean isPlaying) {
            if (!isPlaying && Configure.SHOW_BAIDU_AD) {
            }
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onPlayNext() {

        }
    };

    private void createAd() {
        if (!Configure.SHOW_BAIDU_AD) {
            Log.i(TAG, "createAd: Configure.SHOW_BAIDU_AD is false");
            return;
        }
    }
}
