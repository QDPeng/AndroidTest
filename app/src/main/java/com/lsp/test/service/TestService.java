package com.lsp.test.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lsp.test.socket.HeartBeat;
import com.lsp.test.utils.ActionUtil;
import com.lsp.test.utils.LogUtil;

/**
 * Created by xian on 2015/10/12.
 */
public class TestService extends Service {
    private MyBroadcast receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        regReceiver();
        LogUtil.i("TestService", "onCreate()");
    }

    private void regReceiver() {
        receiver = new MyBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ActionUtil.HEART_BEAT_BROADCAST_START);
        filter.addAction(ActionUtil.HEART_BEAT_BROADCAST_STOP);
        registerReceiver(receiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("TestService", "onStartCommand_startID=" + startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        LogUtil.i("TestService", "onDestroy()");
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private class MyBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ActionUtil.HEART_BEAT_BROADCAST_START)) {
                HeartBeat.getInstancence(getApplicationContext()).start();
            } else if (action.equals(ActionUtil.HEART_BEAT_BROADCAST_STOP)) {
                HeartBeat.getInstancence(getApplicationContext()).stop();
            }

        }
    }

}
