package com.example.media.handler.binary;

import com.example.media.common.bo.MediaBinaryData;
import io.netty.channel.Channel;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:02
 */
public interface BinaryMessageHandler {

    /**
     * 获取消息类型
     *
     * @return {@link com.example.media.common.enums.BinaryMessageType}
     */
    int getHandlerType();

    /**
     * 处理消息实现
     *
     * @param channel 当前channel
     * @param data    二进制流数据
     */
    void handle(Channel channel, MediaBinaryData data);
}
