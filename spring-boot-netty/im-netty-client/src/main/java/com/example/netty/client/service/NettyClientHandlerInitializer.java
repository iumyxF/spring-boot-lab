package com.example.netty.client.service;

import com.example.netty.common.codec.InvocationDecoder;
import com.example.netty.common.codec.InvocationEncoder;
import com.example.netty.common.dispatcher.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: handler初始化器
 * @Date 2023/3/14 15:58
 * @Author fzy
 */
@Component
public class NettyClientHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 60;

    @Resource
    private MessageDispatcher messageDispatcher;

    @Resource
    private NettyClientHandler nettyClientHandler;

    /**
     * Netty 提供了 IdleStateHandler 处理器，提供空闲检测的功能，在 Channel 的读或者写空闲时间太长时，将会触发一个 IdleStateEvent 事件。
     */
    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline()
                // 空闲检测，每60秒发送心跳
                .addLast(new IdleStateHandler(READ_TIMEOUT_SECONDS, 0, 0))
                //设置了180秒为超时
                .addLast(new ReadTimeoutHandler(3 * READ_TIMEOUT_SECONDS))
                // 编码器
                .addLast(new InvocationEncoder())
                // 解码器
                .addLast(new InvocationDecoder())
                // 消息分发器
                .addLast(messageDispatcher)
                // 客户端处理器
                .addLast(nettyClientHandler);
    }
}
