package com.example.netty.client.service;

import com.example.netty.client.message.heartbeat.HeartbeatRequest;
import com.example.netty.common.codec.Invocation;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



/**
 * @description:
 * @Date 2023/3/14 16:02
 * @Author fzy
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO 发起重连 nettyClient.reconnect(); 循环依赖待解决

        // 继续触发事件
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("[exceptionCaught][连接({}) 发生异常]", ctx.channel().id(), cause);
        // 断开连接
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object event) throws Exception {
        // 空闲时，向服务端发起一次心跳
        if (event instanceof IdleStateEvent) {
            log.info("[userEventTriggered][发起一次心跳]");
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            ctx.writeAndFlush(new Invocation(HeartbeatRequest.TYPE, heartbeatRequest))
                    .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        } else {
            super.userEventTriggered(ctx, event);
        }
    }
}
