package com.lsp.test;

import android.app.Application;
import android.content.Intent;

import com.lsp.test.service.TestService;
import com.lsp.test.utils.LogUtil;

/**
 * Created by xian on 2015/10/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i("MyApplication", "onCreate()");
        startTestService();
    }

    private void startTestService() {
        Intent intent = new Intent(this, TestService.class);
        startService(intent);
    }
}
