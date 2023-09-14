package com.example.netty.http.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @description: channel初始化，添加handler处理逻辑
 * @Date 2023/3/9 14:58
 * @author iumyxF
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 如果每个客户端连接都新建一个ChannelHandler实例，当有大量客户端时，服务器将保存大量的ChannelHandler实例。
     * 为此，Netty提供了Sharable注解，如果一个ChannelHandler状态无关，
     * 那么可将其标注为Sharable，如此，服务器只需保存一个实例就能处理所有客户端的事件。
     */
    @Override
    protected void initChannel(SocketChannel channel) {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8,in
        channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        // 解码转String，注意调整自己的编码格式GBK、UTF-8,out
        channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        // 在管道中添加我们自己的接收数据实现方法,in
        channel.pipeline().addLast(new MyServerHandler());
    }
}
