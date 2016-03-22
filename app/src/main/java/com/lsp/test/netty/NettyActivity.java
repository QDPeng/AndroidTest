package com.lsp.test.netty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lsp.test.R;
import com.lsp.test.netty.beans.NettyConstant;
import com.lsp.test.utils.LogUtil;

public class NettyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netty);
        findViewById(R.id.send_heart_beat).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_heart_beat:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            new HeartBeatClient().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
                        } catch (Exception e) {
                            LogUtil.i("nettyActivity", e.toString());
                        }
                    }
                }.start();

                break;
        }
    }
}
