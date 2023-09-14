package com.example.netty.flowrate.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @Date 2023/3/27 16:42
 * @author iumyxF
 */
public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //流量整形
        channel.pipeline().addLast(new ChannelTrafficShapingHandler(10, 10));
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8
        channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }
}
