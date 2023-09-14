package com.example.netty.flowrate.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @Date 2023/3/27 16:43
 * @author iumyxF
 */
public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {

        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 流量整形；writeLimit/readLimit{0 or a limit in bytes/s}
        channel.pipeline().addLast(new GlobalTrafficShapingHandler(channel.eventLoop().parent(), 10, 10));
        // 解码转String
        channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        // 解码转String
        channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new AbstractServerHandler());

    }
}
