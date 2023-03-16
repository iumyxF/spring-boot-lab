package com.example.netty.udp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @description: udp channel初始化器,这里不再是socketChannel 而是NioDatagramChannel
 * @Date 2023/3/13 9:39
 * @Author fzy
 */
public class MyClientChannelInitializer extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel ch) {
        ch.pipeline().addLast(new MyClientHandler());
    }
}
