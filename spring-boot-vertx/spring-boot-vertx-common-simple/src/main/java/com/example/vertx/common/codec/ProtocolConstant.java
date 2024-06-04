package com.example.vertx.common.codec;

/**
 * @author iumyxF
 * @description: 协议常量
 * @date 2024/6/1 15:56
 */
public interface ProtocolConstant {

    /**
     * 消息头长度 17个字节长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 1;
}
