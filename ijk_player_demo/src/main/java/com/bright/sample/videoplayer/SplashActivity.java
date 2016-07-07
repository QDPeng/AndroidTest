package com.bright.sample.videoplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.bright.sample.videoplayer.constant.Configure;
/**
 * 实时开屏，广告实时请求并且立即展现
 */
public class SplashActivity extends Activity {
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Configure.SHOW_BAIDU_AD) {
            Intent intent = new Intent(SplashActivity.this, VideoActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
            finish();
            return;
        }

        setContentView(R.layout.activity_splash);
    }



}
