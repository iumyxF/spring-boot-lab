package com.example.netty.mediaclient.client;

import com.example.netty.mediaclient.entities.MediaBinaryData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

/**
 * @author fzy
 * @description:
 * @date 2024/11/22 9:54
 */
@ChannelHandler.Sharable
public class MyEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        if (msg instanceof MediaBinaryData) {
            MediaBinaryData data = (MediaBinaryData) msg;
            ByteBuf buf = ctx.alloc().directBuffer(data.getLength() + 15);
            buf.writeByte((byte) '$');
            buf.writeByte(data.getChannel());
            buf.writeByte(data.getType());
            buf.writeInt(data.getId());
            buf.writeInt(data.getCodeType());
            buf.writeInt(data.getLength());
            buf.writeBytes(data.getData());
            ctx.writeAndFlush(buf, promise);
        } else if (msg instanceof String) {
            String message = msg + "\n";
            byte[] data = message.getBytes(StandardCharsets.UTF_8);
            ByteBuf buf = ctx.alloc().directBuffer(data.length);
            buf.writeBytes(data);
            ctx.writeAndFlush(buf, promise);
        }
    }
}
