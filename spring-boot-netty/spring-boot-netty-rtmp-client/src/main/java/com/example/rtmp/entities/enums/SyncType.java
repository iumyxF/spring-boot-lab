package com.example.rtmp.entities.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 17:01
 */
public enum SyncType {

    /**
     * 桌面同屏
     */
    DESKTOP(1),

    /**
     * 编码器同屏
     */
    ENCODER(2);

    private final Integer type;

    private final static Map<Integer, SyncType> TYPE_MAP = new HashMap<>();

    static {
        Arrays.stream(SyncType.values()).forEach(type -> TYPE_MAP.put(type.getType(), type));
    }

    SyncType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static SyncType getByType(Integer type) {
        return TYPE_MAP.get(type);
    }

}
