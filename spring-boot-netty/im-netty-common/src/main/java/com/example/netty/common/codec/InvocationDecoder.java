package com.example.netty.common.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @description: 协议消息编码器
 * @Date 2023/3/14 11:00
 * @author iumyxF
 */
@Slf4j
public class InvocationDecoder extends ByteToMessageDecoder {
    /**
     * 字节的长度
     */
    private int byteLength = 4;

    /**
     * 反序列化，将缓冲区字节数据转成invocation实体
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 标记当前读取位置
        in.markReaderIndex();
        // 判断是否能够读取 length 长度
        if (in.readableBytes() <= byteLength) {
            return;
        }
        // 读取字节数粗的总长度
        int length = in.readInt();
        if (length < 0) {
            throw new CorruptedFrameException("negative length: " + length);
        }
        // 如果 当前字节数组的可读长度小于length，则退回到原读取位置
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        // 读取内容
        byte[] content = new byte[length];
        in.readBytes(content);
        // 解析成 Invocation
        Invocation invocation = JSON.parseObject(content, Invocation.class);
        out.add(invocation);
        log.info("[decode][连接({}) 解析到一条消息({})]", ctx.channel().id(), invocation.toString());
    }
}
