package com.example.netty.common.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 协议消息编码器
 * @Date 2023/3/14 11:02
 * @Author fzy
 */
@Slf4j
public class InvocationEncoder extends MessageToByteEncoder<Invocation> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Invocation msg, ByteBuf out) {
        // 将 Invocation 转换成 byte[] 数组
        byte[] content = JSON.toJSONBytes(msg);
        // 写入 length
        out.writeInt(content.length);
        // 写入内容
        out.writeBytes(content);
        log.info("[encode][连接({}) 编码了一条消息({})]", ctx.channel().id(), msg.toString());
    }
}
