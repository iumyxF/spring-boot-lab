package com.example.media.codec;

import com.example.media.common.bo.MediaBinaryData;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:00
 */
@Slf4j
public class TcpEncoder extends MessageToByteEncoder<MediaBinaryData> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MediaBinaryData mediaBinaryData, ByteBuf byteBuf) {
        if (null == mediaBinaryData) {
            return;
        }
        ByteBuf buffer = null;
        try {
            buffer = ctx.alloc().buffer(15 + mediaBinaryData.getLength());
            mediaBinaryData.writeToBuf(buffer);
            byte[] bytes = ByteBufUtil.getBytes(buffer);
            byteBuf.writeBytes(bytes);
        } finally {
            // 需要释放申请的buff 否则内存会溢出
            if (null != buffer) {
                ReferenceCountUtil.release(buffer);
            }
        }
    }
}
