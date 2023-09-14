package com.example.netty.protostuff.server;

import com.example.netty.protostuff.codec.ObjDecoder;
import com.example.netty.protostuff.codec.ObjEncoder;
import com.example.netty.protostuff.domain.MsgInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @description:
 * @Date 2023/3/10 15:50
 * @author iumyxF
 */
public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}
