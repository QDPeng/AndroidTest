package com.lsp.test.netty;

import com.lsp.test.netty.beans.NettyConstant;
import com.lsp.test.netty.handler.HeartBeatReqHandler;
import com.lsp.test.netty.handler.LoginAuthReqHandler;
import com.lsp.test.netty.util.NettyMessageDecoder;
import com.lsp.test.netty.util.NettyMessageEncoder;
import com.lsp.test.utils.LogUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 心跳客户端
 */
public class HeartBeatClient {
    private ScheduledExecutorService executor = Executors
            .newScheduledThreadPool(1);


    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        try {
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    LogUtil.i("HeartBeatClient", "initChannel");
//                    ch.pipeline().addLast(
//                            new NettyMessageDecoder(1024 * 1024, 4, 4));
//                    ch.pipeline().addLast("MessageEncoder",
//                            new NettyMessageEncoder());
                    ch.pipeline().addLast("readTimeoutHandler",
                            new ReadTimeoutHandler(50));
                    ch.pipeline().addLast("LoginAuthHandler",
                            new LoginAuthReqHandler());
                    ch.pipeline().addLast("HeartBeatHandler",
                            new HeartBeatReqHandler());

                }
            });
            // 发起异步连接操作
            LogUtil.i("HeartBeatClient", "connect");
//            ChannelFuture future = bootstrap.connect(host,port).sync();
//            LogUtil.i("HeartBeatClient", "closeFuture()");
//            future.channel().closeFuture().sync();

            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        try {
                            connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作
                        } catch (Exception e) {
                            LogUtil.i("HeartBeatClient", e.toString());
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
