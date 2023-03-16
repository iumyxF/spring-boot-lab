package com.example.netty.heartbeat.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

/**
 * @description: 重试机制
 * @Date 2023/3/16 14:28
 * @Author fzy
 */
public class MyClientChannelFutureListener implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture future) {
        if (future.isSuccess()) {
            System.out.println("client start done.");
            return;
        }
        EventLoop eventLoop = future.channel().eventLoop();
        eventLoop.schedule(() -> {
            new NettyClient().connect("127.0.0.1", 7397);
            System.out.println("client start done. ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 1L, TimeUnit.SECONDS);
    }
}
