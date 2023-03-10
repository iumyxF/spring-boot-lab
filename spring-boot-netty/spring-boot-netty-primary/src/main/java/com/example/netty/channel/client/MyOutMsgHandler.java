package com.example.netty.channel.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.Date;

/**
 * @description: 客户端处理出站信息
 * @Date 2023/3/10 13:56
 * @Author fzy
 */
public class MyOutMsgHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new Date() + " : ChannelOutboundHandlerAdapter.read 发来一条消息\r\n");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.writeAndFlush(new Date() + " : ChannelOutboundHandlerAdapter.write 发来一条消息\r\n");
        super.write(ctx, msg, promise);
    }
}
