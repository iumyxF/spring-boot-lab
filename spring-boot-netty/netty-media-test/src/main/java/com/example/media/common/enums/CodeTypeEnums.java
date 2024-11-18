package com.example.media.common.enums;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 20:01
 */
public enum CodeTypeEnums {

    /**
     * 默认或其他
     */
    DEFAULT_CODE(0, "默认或其他"),

    /**
     * 硬编码
     */
    HARD_CODE(1, "硬编码"),

    /**
     * 软编码
     */
    SOFT_CODE(2, "软编码");

    private final int value;
    private final String text;

    CodeTypeEnums(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
