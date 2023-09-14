package com.example.netty.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @Date 2023/3/16 14:28
 * @author iumyxF
 */
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    private static final ThreadPoolTaskExecutor EXECUTOR = new ThreadPoolTaskExecutor();

    static {
        int core = Runtime.getRuntime().availableProcessors() + 1;
        EXECUTOR.setCorePoolSize(core);
        EXECUTOR.setMaxPoolSize(core * 2);
        //队列
        EXECUTOR.setQueueCapacity(128);
        //等待时间
        EXECUTOR.setKeepAliveSeconds(300);
        EXECUTOR.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化
        EXECUTOR.initialize();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：本客户端链接到服务端。channelId：" + channel.id());
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    /**
     * 服务端close掉channel，客户端就会触发 channelInactive
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("断开链接重连" + ctx.channel().localAddress().toString());
        EXECUTOR.submit(() -> {
            try {
                new NettyClient().connect("127.0.0.1", 7397);
                System.out.println("client start done.");
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("client start error go reconnect ...");
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("异常信息，断开重连：\r\n" + cause.getMessage());
        ctx.close();
    }
}
