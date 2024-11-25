package com.example.netty.mediaclient.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fzy
 * @description: 二进制流媒体数据
 * @date 2023/3/17 16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaBinaryData implements Serializable {

    private static final long serialVersionUID = 129307129083L;

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
     */
    private byte type;

    /**
     * 单元号  32 bit
     */
    private int id;

    /**
     * 32 bit
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
}