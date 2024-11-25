package com.example.netty.mediaclient.client;

import com.example.netty.mediaclient.entities.MediaBinaryData;
import com.example.netty.mediaclient.entities.MediaJsonData;
import com.example.netty.mediaclient.utils.JacksonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/22 10:01
 */
public class MyDecoder extends ByteToMessageDecoder {

    private static final int mediaBinaryLength = 15;

    /**
     * 协议头 $ = 36
     */
    private static final byte protocolHead = '$';

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        in.markReaderIndex();
        if (in.readableBytes() < mediaBinaryLength) {
            return;
        }
        byte head = in.readByte();
        in.resetReaderIndex();
        if (head == protocolHead) {
            MediaBinaryData binaryData = decodeBinary(in);
            if (null != binaryData) {
                out.add(binaryData);
            }
        } else {
            MediaJsonData mediaJsonData = decodeJson(in);
            if (null != mediaJsonData) {
                out.add(mediaJsonData);
            }
        }
    }

    private MediaBinaryData decodeBinary(ByteBuf in) {
        //协议头
        byte head = in.readByte();
        //通道号
        byte channel = in.readByte();
        //开始位+结束位+类型+保留位
        byte typeData = in.readByte();
        //单元号
        int id = in.readInt();
        //编码类型
        int codeType = in.readInt();
        //数据长度
        int length = in.readInt();
        //可读数据
        int readable = in.readableBytes();
        if (length < 0 || readable < length) {
            in.resetReaderIndex();
            return null;
        }
        // 读取内容
        byte[] data = new byte[length];
        in.readBytes(data);
        //构建消息体
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

    private MediaJsonData decodeJson(ByteBuf in) {
        in.markReaderIndex();
        //记录json数据长度
        int length = 0;
        while (in.readableBytes() > 0) {
            byte b = in.readByte();
            length++;
            //json结束标识符
            if (b == (byte) '\n' || b == (byte) '\r') {
                in.resetReaderIndex();
                byte[] data = new byte[length];
                in.readBytes(data);
                String str = new String(data);
                //解析类型数据
                return JacksonUtil.readValue(str, MediaJsonData.class);
            }
        }
        in.resetReaderIndex();
        return null;
    }
}
