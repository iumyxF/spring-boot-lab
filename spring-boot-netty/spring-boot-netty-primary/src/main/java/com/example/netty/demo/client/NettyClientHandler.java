package com.example.netty.demo.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @description: 客户端 消息处理
 * @Date 2023/3/9 10:28
 * @author iumyxF
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当通道就绪就会触发
     *
     * @param ctx 上下文channel
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("[客户端] : 上下文对象 ctx = " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("[客户端] : 服务端我需要用户列表数据...", CharsetUtil.UTF_8));
    }

    /**
     * 当通道有读取事件时，会触发
     *
     * @param ctx 上下文channel
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("[客户端] : 接受服务器回复的消息：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("[客户端] : 接受服务器的地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
