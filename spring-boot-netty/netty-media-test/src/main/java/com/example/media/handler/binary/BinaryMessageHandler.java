package com.example.media.handler.binary;

import com.example.media.common.bo.MediaBinaryData;
import io.netty.channel.Channel;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:02
 */
public interface BinaryMessageHandler {

    int getHandlerType();

    void handle(Channel channel, MediaBinaryData data);
}
