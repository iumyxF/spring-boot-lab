package com.example.media.common.enums;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:05
 */
public enum JsonMessageType {

    REGISTER(1, "注册");

    private final int value;

    private final String text;

    JsonMessageType(int value, String text) {
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
