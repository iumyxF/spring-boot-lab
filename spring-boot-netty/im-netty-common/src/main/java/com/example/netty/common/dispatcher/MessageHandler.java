package com.example.netty.common.dispatcher;

import io.netty.channel.Channel;

/**
 * @description: 消息处理器
 * @Date 2023/3/14 11:04
 * @author iumyxF
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
