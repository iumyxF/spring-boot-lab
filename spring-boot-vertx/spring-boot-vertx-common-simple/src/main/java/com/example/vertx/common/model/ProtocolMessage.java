package com.example.vertx.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author iumyxF
 * @description: 传续协议消息体
 * <p>
 * |header|body
 * header: 一个17字节 ，（1字节魔数|1字节版本号|1字节序列化器|1字节消息类型|1字节状态|8字节请求id|4字节消息体长度）
 * @date 2024/6/1 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolMessage<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息头
     */
    private Header header;

    /**
     * 消息体（请求或响应对象）
     */
    private T body;

    /**
     * 协议消息头
     */
    @Data
    public static class Header {

        /**
         * 魔数，保证安全性
         */
        private byte magic;

        /**
         * 版本号
         */
        private byte version;

        /**
         * 序列化器
         */
        private byte serializer;

        /**
         * 消息类型（请求 / 响应）
         */
        private byte type;

        /**
         * 状态
         */
        private byte status;

        /**
         * 请求 id
         */
        private long requestId;

        /**
         * 消息体长度
         */
        private int bodyLength;
    }
}
