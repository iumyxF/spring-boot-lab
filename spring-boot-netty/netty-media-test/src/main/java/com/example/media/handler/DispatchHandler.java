package com.example.media.handler;

import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.bo.MediaJsonData;
import com.example.media.handler.binary.TcpBinaryHandler;
import com.example.media.handler.json.RegisterHandler;
import com.example.media.manager.NettyChannelManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:48
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class DispatchHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private NettyChannelManager manager;
    @Resource
    private RegisterHandler registerHandler;
    @Resource
    private TcpBinaryHandler tcpBinaryHandler;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof MediaBinaryData) {
            // 二进制消息处理
            tcpBinaryHandler.handle(ctx.channel(), (MediaBinaryData) msg);
        } else if (msg instanceof MediaJsonData) {
            // 处理注册消息
            registerHandler.handle(ctx.channel(), (MediaJsonData) msg);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                log.debug("流媒体读超时");
                ctx.fireChannelInactive();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                log.debug("流媒体写超时");
            } else if (e.state() == IdleState.ALL_IDLE) {
                log.debug("流媒体读写超时");
            }
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        manager.unregister(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("[exceptionCaught][连接({}) 发生异常]", ctx.channel().id(), cause);
        manager.unregister(ctx.channel());
        ctx.channel().close();
    }
}
