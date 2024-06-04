package com.example.vertx.common.codec;

import com.example.vertx.common.model.ProtocolMessage;
import com.example.vertx.common.serializer.JsonSerializer;
import com.example.vertx.common.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;

import java.io.IOException;

/**
 * @author iumyxF
 * @description: 协议编码
 * @date 2024/6/1 16:27
 */
public class ProtocolMessageEncoder {

    private final static Serializer SERIALIZER = new JsonSerializer();

    /**
     * 编码
     *
     * @param protocolMessage
     * @return
     * @throws IOException
     */
    public static byte[] encode(ProtocolMessage<?> protocolMessage) throws IOException {
        if (protocolMessage == null || protocolMessage.getHeader() == null) {
            return new byte[0];
        }
        ProtocolMessage.Header header = protocolMessage.getHeader();
        // 依次向缓冲区写入字节
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeByte(header.getMagic());
        buffer.writeByte(header.getVersion());
        buffer.writeByte(header.getSerializer());
        buffer.writeByte(header.getType());
        buffer.writeByte(header.getStatus());
        buffer.writeLong(header.getRequestId());
        // 获取序列化器
        byte[] bodyBytes = SERIALIZER.serialize(protocolMessage.getBody());
        // 写入 body 长度和数据
        buffer.writeInt(bodyBytes.length);
        buffer.writeBytes(bodyBytes);
        // 解决buffer.array(); 导致的UnsupportedOperationException
        return ByteBufUtil.getBytes(buffer);
    }
}
