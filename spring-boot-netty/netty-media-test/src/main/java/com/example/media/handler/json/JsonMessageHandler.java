package com.example.media.handler.json;

import com.example.media.common.bo.MediaJsonData;
import io.netty.channel.Channel;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:04
 */
public interface JsonMessageHandler {

    int getHandlerType();

    void handle(Channel channel, MediaJsonData data);
}
