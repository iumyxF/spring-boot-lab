package com.example.netty.common.dispatcher;

import io.netty.channel.Channel;

/**
 * @description: 消息分发处理
 * @Date 2023/3/14 11:04
 * @Author fzy
 */
public interface MessageHandler<T extends Message> {

    /**
     * 处理消息
     *
     * @param channel 消息channel
     * @param message 消息实体
     */
    void execute(Channel channel, T message);

    /**
     * 获取消息类型
     *
     * @return Message TYPE字段
     */
    String getType();
}
