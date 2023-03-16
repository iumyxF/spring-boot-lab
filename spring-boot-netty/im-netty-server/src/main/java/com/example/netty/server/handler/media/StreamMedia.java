package com.example.netty.server.handler.media;

/**
 * @description: 流媒体数据
 * @Date 2023/3/15 17:43
 * @Author fzy
 */
public class StreamMedia {
    /**
     * 协议头 8bit
     */
    private byte head;

    /**
     * 通道号   8 bit
     */
    private byte channel;

    /**
     * 开始位 1bit
     * 结束位 1bit
     * 类型位 4bit
     * 保留位 2bit
     */
    private byte type;

    /**
     * 单元号 32bit
     */
    private int id;

    /**
     * 数据长度 32bit
     */
    private int length;

    /**
     * 数据
     */
    private byte[] data;
}
