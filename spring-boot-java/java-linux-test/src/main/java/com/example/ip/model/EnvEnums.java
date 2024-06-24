package com.example.ip.model;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/17 15:59
 */
public enum EnvEnums {
    /**
     * centos
     */
    CENTOS(0),
    /**
     * ubuntu
     */
    UBUNTU(1),
    /**
     * windows
     */
    WINDOWS(2);

    private final Integer value;

    EnvEnums(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
