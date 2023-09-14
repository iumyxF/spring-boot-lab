package com.example.netty.server.service;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 服务端 Channel 实现类，提供对客户端 Channel 建立连接、断开连接、异常时的处理
 * @Date 2023/3/14 14:42
 * @author iumyxF
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private NettyChannelManager channelManager;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 从管理器中添加
        channelManager.add(ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        // 从管理器中移除
        channelManager.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("[exceptionCaught][连接({}) 发生异常]", ctx.channel().id(), cause);
        // 断开连接
        ctx.channel().close();
    }

}
