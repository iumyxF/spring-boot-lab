package com.example.media.codec;

import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.bo.MediaJsonData;
import com.example.media.utils.JacksonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:00
 */
@Slf4j
public class TcpDecoder extends ByteToMessageDecoder {

    /**
     * 一个数据包最少长度是15
     */
    private static final int MINIMUM_LENGTH = 15;

    /**
     * 协议头 $ = 36
     */
    private static final byte PROTOCOL_HEAD = '$';


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        // 标记当前读取位置
        byteBuf.markReaderIndex();
        if (byteBuf.readableBytes() < MINIMUM_LENGTH) {
            return;
        }
        byte head = byteBuf.readByte();
        byteBuf.resetReaderIndex();
        if (head == PROTOCOL_HEAD) {
            // Binary
            MediaBinaryData binaryData = decodeBinaryData(byteBuf);
            if (null == binaryData) {
                return;
            }
            list.add(binaryData);
        } else {
            // JSON
            MediaJsonData jsonData = decodeJsonData(byteBuf);
            list.add(jsonData);
        }
    }

    /**
     * 解析二进制协议
     */
    private MediaBinaryData decodeBinaryData(ByteBuf in) {
        // 协议头
        byte head = in.readByte();
        // 通道号
        byte channel = in.readByte();
        // 开始位+结束位+类型+保留位
        byte typeData = in.readByte();
        // 单元号
        int id = in.readInt();
        // 编码类型
        int codeType = in.readInt();
        // 数据长度
        int length = in.readInt();
        // 可读数据
        int readable = in.readableBytes();
        if (length < 0 || readable < length) {
            in.resetReaderIndex();
            return null;
        }
        // 读取内容
        byte[] data = new byte[length];
        in.readBytes(data);
        // 构建消息体
        MediaBinaryData binaryData = new MediaBinaryData();
        binaryData.setHead(head);
        binaryData.setChannel(channel);
        binaryData.setType(typeData);
        binaryData.setId(id);
        binaryData.setCodeType(codeType);
        binaryData.setLength(length);
        binaryData.setData(data);
        return binaryData;
    }

    /**
     * 解析JSON协议
     */
    private MediaJsonData decodeJsonData(ByteBuf in) {
        in.markReaderIndex();
        int length = 0;
        int maxLength = 1024;
        boolean isJsonFormat = false;
        while (in.readableBytes() > 0 && length < maxLength) {
            byte b = in.readByte();
            length++;
            if (b == (byte) '\n' || b == (byte) '\r') {
                isJsonFormat = true;
                break;
            }
        }
        in.resetReaderIndex();
        // 如果是合法JSON就转化
        if (isJsonFormat) {
            ByteBuf slice = in.readSlice(length);
            try {
                String str = slice.toString(StandardCharsets.UTF_8);
                return JacksonUtil.readValue(str, MediaJsonData.class);
            } finally {
                slice.release();
            }
        }
        return null;
    }

}
