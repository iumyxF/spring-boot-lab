package com.example.vertx.common.codec;

/**
 * @author fzy
 * @description: 协议消息状态枚举
 * @date 2024/6/3 10:35
 */
public enum ProtocolMessageStatusEnum {

    /**
     * 成功
     */
    OK("ok", 20),

    /**
     * 异常请求
     */
    BAD_REQUEST("badRequest", 40),

    /**
     * 异常响应
     */
    BAD_RESPONSE("badResponse", 50);

    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public int getValue() {
        return value;
    }
}
