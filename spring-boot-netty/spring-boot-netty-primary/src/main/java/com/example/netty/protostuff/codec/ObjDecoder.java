package com.example.netty.protostuff.codec;

import com.example.netty.protostuff.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @description: 编码器 将java对象转换成二进制数据
 * 简介：
 * protostuff 基于Google protobuf，但是提供了更多的功能和更简易的用法。
 * 其中，protostuff-runtime 实现了无需预编译对java bean进行protobuf序列化/反序列化的能力。
 * protostuff-runtime的局限是序列化前需预先传入schema，反序列化不负责对象的创建只负责复制，因而必须提供默认构造函数。
 * 此外，protostuff 还可以按照protobuf的配置序列化成json/yaml/xml等格式。
 * 在性能上，protostuff不输原生的protobuf，甚至有反超之势。
 * @Date 2023/3/10 10:56
 * @Author fzy
 */
public class ObjDecoder extends ByteToMessageDecoder {

    private static final int BYTE_LENGTH = 4;

    private final Class<?> genericClass;

    public ObjDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < BYTE_LENGTH) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);

        out.add(SerializationUtil.deserialize(data, genericClass));
    }
}
