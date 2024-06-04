package com.example.vertx.common.codec;

/**
 * @author iumyxF
 * @description: 消息协议枚举
 * @date 2024/6/1 16:15
 */
public enum ProtocolMessageTypeEnum {

    /**
     * 请求类型
     */
    REQUEST(0),

    /**
     * 响应类型
     */
    RESPONSE(1),

    /**
     * 心跳类型
     */
    HEART_BEAT(2),

    /**
     * 其他类型
     */
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    /**
     * 根据 key 获取枚举
     *
     * @param key
     * @return
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum anEnum : ProtocolMessageTypeEnum.values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }
}
