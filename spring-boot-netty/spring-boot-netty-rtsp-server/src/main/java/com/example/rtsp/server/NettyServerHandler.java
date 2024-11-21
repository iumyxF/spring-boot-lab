package com.example.rtsp.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 服务端 Channel 实现类，提供对客户端 Channel 建立连接、断开连接、异常时的处理
 * @Date 2023/3/14 14:42
 * @author iumyxF
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("[服务端] 有新的channel 加入" + System.currentTimeMillis());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead ...");
        super.channelRead(ctx, msg);
    }
}
