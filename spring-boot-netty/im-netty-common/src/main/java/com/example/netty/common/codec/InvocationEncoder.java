package com.example.netty.common.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 协议消息编码器
 * @Date 2023/3/14 11:02
 * @author iumyxF
 */
@Slf4j
public class InvocationEncoder extends MessageToByteEncoder<Invocation> {

    /**
     * 将消息序列化，发送出去
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Invocation msg, ByteBuf out) {
        // 将 Invocation 转换成 byte[] 数组
        byte[] content = JSON.toJSONBytes(msg);
        // 写入 length , 写入消息长度是为了解决粘包问题
        out.writeInt(content.length);
        // 写入内容
        out.writeBytes(content);
        // 写入的内容，一个包 ==> [Invocation字节数组长度][Invocation字节数组]
        log.info("[encode][连接({}) 编码了一条消息({})]", ctx.channel().id(), msg.toString());
    }
}
