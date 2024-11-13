package com.example.media.handler.binary;

import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.enums.BinaryMessageType;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:02
 */
@Component
public class HeartBeatHandler implements BinaryMessageHandler {
    @Override
    public int getHandlerType() {
        return BinaryMessageType.HEART_BEAT.getValue();
    }

    @Override
    public void handle(Channel channel, MediaBinaryData data) {

    }
}
