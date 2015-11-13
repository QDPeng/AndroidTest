package com.lsp.test.socket;

import android.content.Context;

import com.feiche.driver.socket_test.service.Client;
import com.feiche.driver.socket_test.service.ISocketResponse;
import com.feiche.driver.socket_test.service.Packet;
import com.lsp.test.utils.LogUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xian on 2015/10/12.
 */
public class HeartBeat {
    public static HeartBeat mInstance;
    private ExecutorService executor;
    private Worker worker;// 工作线程
    private volatile boolean canSend = false;
    private Client client;
    private String sendStr = "习近平在唁电中表示，施密特先生为德国国家建设和欧洲一体化进程作出了不懈努力，赢得了世人尊重。40年前，施密特先生同中国老一辈领导人共同开启了中德友好合作的大门，为中德关系发展作出了积极贡献。";

    public HeartBeat(Context context) {
        executor = Executors.newSingleThreadExecutor();
        worker = new Worker();
        client = new Client(context, new ISocketResponse() {
            @Override
            public void onSocketResponse(String txt) {
                LogUtil.i("onSocketResponse-->", txt);
            }
        });
    }

    public static HeartBeat getInstancence(Context context) {
        if (mInstance == null) {
            synchronized (HeartBeat.class) {
                if (mInstance == null) {
                    mInstance = new HeartBeat(context);
                }
            }
        }
        return mInstance;
    }

    public void start() {
        canSend = true;
        executor.execute(worker);
    }

    public void stop() {
        canSend = false;
        executor.shutdown();
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            if (canSend) {
                Packet packet = new Packet();
                packet.pack(sendStr);
                client.open();
                client.send(packet);
                LogUtil.i("HeartBeat", "send a heart beat");
            }

        }

    }

}
