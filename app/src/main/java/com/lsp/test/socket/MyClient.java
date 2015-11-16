package com.lsp.test.socket;

import android.util.Log;

import com.feiche.driver.socket_test.util.*;
import com.lsp.test.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by xian on 2015/11/13.
 */
public class MyClient {
    private static final int STATE_OPEN = 1;//socket打开
    private static final int STATE_CLOSE = 1 << 1;//socket关闭
    private static final int STATE_CONNECT_START = 1 << 2;//开始连接server
    private static final int STATE_CONNECT_SUCCESS = 1 << 3;//连接成功
    private static final int STATE_CONNECT_FAILED = 1 << 4;//连接失败
    private static final int STATE_CONNECT_WAIT = 1 << 5;//等待连接
    private int state = STATE_CONNECT_START;
    private String IP = "192.168.1.67";
    private int PORT = 10000;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Object lock = new Object();
    private Thread conn;//连接线程
    private Thread write;//写线程
    private Thread read;//读线程
    private String sendStr = "习近平在唁电中表示，施密特先生为德国国家建设和欧洲一体化进程作出了不懈努力，赢得了世人尊重。40年前，施密特先生同中国老一辈领导人共同开启了中德友好合作的大门，为中德关系发展作出了积极贡献。";


    public void openConnect() {
//        if (conn != null) {
//            synchronized (lock) {
//                lock.notifyAll();
//            }
//            LogUtil.i("My_client", "conn != null" );
//        } else {
        conn = new Thread() {
            @Override
            public void run() {
                connect();
                if (state == STATE_CONNECT_SUCCESS) {
                    LogUtil.i("My_client", "STATE_CONNECT_SUCCESS");
                    openRead();
                    openWrite();
                }


            }
        };
        conn.start();
        LogUtil.i("My_client", "conn == null");
        // }
    }


    private void connect() {
        try {
            if (socket == null) {
                socket = new Socket();
            }
            if (!socket.isConnected()){

                socket.connect(new InetSocketAddress(IP, PORT), 10 * 100);
            }
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            state = STATE_CONNECT_SUCCESS;//连接成功
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openRead() {
//        if (read != null) {
//            LogUtil.i("My_client", "read != null");
//            synchronized (lock) {
//                lock.notifyAll();
//            }
//        } else {
            read = new Thread() {
                @Override
                public void run() {
                    readMsg();

                }
            };
            read.start();
            LogUtil.i("My_client", "read == null");
      //  }
    }

    private synchronized void readMsg() {
        try {
            while (state != STATE_CLOSE && state == STATE_CONNECT_SUCCESS && null != inputStream) {
                byte[] rec = new byte[8];
                inputStream.read(rec, 0, 8);
                int len = com.feiche.driver.socket_test.util.ByteUtil.getInt(rec, 0);
                int type = com.feiche.driver.socket_test.util.ByteUtil.getInt(rec, 4);
                byte[] str = new byte[len];
                inputStream.read(str, 0, len);
                String recs = new String(str, "utf-8").trim();
                LogUtil.i("My_client", "read_msg:len=" + len + "type=" + type + recs);
                //reconn();//走到这一步，说明服务器socket断了
                break;
            }
        } catch (SocketException e1) {
            e1.printStackTrace();//客户端主动socket.close()会调用这里 java.net.SocketException: Socket closed
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    private void openWrite() {
//        if (write != null) {
//            LogUtil.i("My_client", "write != null");
//            synchronized (lock) {
//                lock.notifyAll();
//            }
//        } else {
            write = new Thread() {
                @Override
                public void run() {
                    writeMsg();


                }
            };
            write.start();
            LogUtil.i("My_client", "read write== null");
       // }
    }


    private void writeMsg() {
        try {
            byte[] send = new byte[8];
            int len = sendStr.getBytes().length;
            ByteUtil.putInt(send, len, 0);// 字节长度
            ByteUtil.putInt(send, 1, 4);// 类型为1
            outputStream.write(send);
            outputStream.write(sendStr.getBytes());
            outputStream.flush();
            LogUtil.i("My_client", "writeMsg_end");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
