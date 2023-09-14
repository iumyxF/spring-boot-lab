package com.example.netty.udp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @description: udp channel初始化器
 * @Date 2023/3/13 9:41
 * @author iumyxF
 */
public class MyServerChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    private final EventLoopGroup group = new NioEventLoopGroup();

    @Override
    protected void initChannel(NioDatagramChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(group, new MyServerHandler());
    }
}
