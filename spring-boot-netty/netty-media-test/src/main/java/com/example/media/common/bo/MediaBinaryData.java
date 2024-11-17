package com.example.media.common.bo;

import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * @author feng
 * @description: 二进制流对象
 * @date 2024/11/12 20:39
 */
@Data
public class MediaBinaryData {

    /**
     * 协议头 '$'
     */
    private byte head;

    /**
     * 通道号   8 bit
     */
    private byte channel;

    /**
     * 开始位 1、结束位 1、类型 4、保留 2    8 bit
     * <p>
     * 读 类型 [3,6] 和 0x0f = 0000 1111 做异或运算
     */
    private byte type;

    /**
     * 单元号  32 bit
     */
    private int id;

    /**
     * 编码类型 32 bit
     * {@link com.example.media.common.enums.BinaryMessageType}
     */
    private int codeType;

    /**
     * 数据长度  32 bit
     */
    private int length;

    /**
     * 数据
     */
    private byte[] data;

    /**
     * 将data数据写入buf
     *
     * @param buf buf
     */
    public void writeToBuf(ByteBuf buf) {
        buf.writeByte(head);
        buf.writeByte(channel);
        buf.writeByte(type);
        buf.writeInt(id);
        buf.writeInt(codeType);
        buf.writeInt(length);
        buf.writeBytes(data);
    }
}