package com.example.netty.common.dispatcher;

import com.alibaba.fastjson.JSON;
import com.example.netty.common.codec.Invocation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 消息分发器
 * @Date 2023/3/14 11:06
 * @author iumyxF
 */
@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Resource
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        // 获得 type 对应的 MessageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(invocation.getType());
        // 获得  MessageHandler 处理器 的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // 解析消息
        Message message = JSON.parseObject(invocation.getMessage(), messageClass);
        // 执行逻辑
        executor.submit(() -> messageHandler.execute(ctx.channel(), message));
    }
}
