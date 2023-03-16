package com.example.netty.heartbeat.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @Date 2023/3/16 14:28
 * @Author fzy
 */
public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        //基于换行符
        ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
        //String编码
        ch.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        //String解码
        ch.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        // 在管道中添加我们自己的接收数据实现方法
        ch.pipeline().addLast(new MyClientHandler());
    }
}
