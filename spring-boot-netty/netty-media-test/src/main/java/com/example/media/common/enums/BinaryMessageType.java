package com.example.media.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 20:04
 */
public enum BinaryMessageType {

    // region 流媒体类型

    /**
     * H264数据
     */
    H264(0, "H264"),

    /**
     * PCM数据
     */
    PCM(1, "PCM"),

    /**
     * AAC数据
     */
    AAC(2, "AAC"),

    /**
     * 测试数据
     */
    TEST(3, "TEST"),

    /**
     * 代理数据
     */
    PROXY(4, "PROXY"),

    /**
     * VP8数据
     */
    VP8(5, "VP8"),

    /**
     * VP9数据
     */
    VP9(6, "VP9"),

    /**
     * H265数据
     */
    H265(7, "H265"),

    /**
     * PDF批注信息
     */
    PDF(8, "PDF"),

    /**
     * 心跳
     */
    HEART_BEAT(9, "HEART_BEAT"),

    /**
     * 图片JPEG格式
     */
    JPEG(10, "JPEG");

    private final int value;

    private final String text;

    public static final Map<Integer, BinaryMessageType> typeMap = new HashMap<>(16);

    static {
        Arrays.stream(BinaryMessageType.values()).forEach(item -> typeMap.put(item.getValue(), item));
    }

    BinaryMessageType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static BinaryMessageType getTypeByValue(int value) {
        return typeMap.get(value);
    }

}
