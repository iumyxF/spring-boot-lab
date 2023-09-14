package com.example.netty.flowrate.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @description:
 * @Date 2023/3/27 16:42
 * @author iumyxF
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println("NettyClient接收到消息：" + msg + " length：" + msg.length());
        ctx.write(UUID.randomUUID() + "\r\n", ctx.voidPromise());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
