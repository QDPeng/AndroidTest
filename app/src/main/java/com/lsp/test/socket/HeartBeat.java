package com.lsp.test.socket;

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

    public HeartBeat() {
        executor = Executors.newSingleThreadExecutor();
        worker=new Worker();
    }

    public static HeartBeat getInstancence() {
        if (mInstance == null) {
            synchronized (HeartBeat.class) {
                if (mInstance == null) {
                    mInstance = new HeartBeat();
                }
            }
        }
        return mInstance;
    }
    public void start(){
        canSend=true;
       executor.execute(worker);
    }
    public void stop(){
        canSend=false;
        executor.shutdown();
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            if (canSend)
            LogUtil.i("HeartBeat", "send a heart beat");
        }

    }

}
