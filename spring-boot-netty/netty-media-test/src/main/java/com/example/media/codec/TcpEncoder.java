package com.example.media.codec;

import com.example.media.common.bo.MediaBinaryData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:00
 */
@Slf4j
public class TcpEncoder extends MessageToByteEncoder<MediaBinaryData> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MediaBinaryData mediaBinaryData, ByteBuf byteBuf) {

    }
}
