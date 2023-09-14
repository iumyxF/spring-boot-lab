package com.example.netty.im.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @description:
 * @Date 2023/3/13 14:42
 * @author iumyxF
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline().addLast("http-codec", new HttpServerCodec());
        channel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
        channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }
}
