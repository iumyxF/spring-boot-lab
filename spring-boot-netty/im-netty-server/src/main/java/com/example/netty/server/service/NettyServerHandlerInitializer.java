package com.example.netty.server.service;

import com.example.netty.common.codec.InvocationDecoder;
import com.example.netty.common.codec.InvocationEncoder;
import com.example.netty.common.dispatcher.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description: handler初始化器
 * @Date 2023/3/14 10:55
 * @Author fzy
 */
@Component
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * 心跳超时时间
     */
    private static final Integer READ_TIMEOUT_SECONDS = 3 * 60;

    //@Resource
    //private MessageDispatcher messageDispatcher;

    @Resource
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(Channel ch) {
        // 获得 Channel 对应的 ChannelPipeline
        ChannelPipeline channelPipeline = ch.pipeline();
        // 添加一堆 NettyServerHandler 到 ChannelPipeline 中
        channelPipeline
                // 空闲检测
                .addLast(new ReadTimeoutHandler(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS))
                // 编码器
                .addLast(new InvocationEncoder())
                // 解码器
                .addLast(new InvocationDecoder())
                // 消息分发器
                //.addLast(messageDispatcher)
                // 服务端处理器
                .addLast(nettyServerHandler);
    }
}
