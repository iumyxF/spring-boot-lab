package com.example.files.service.upload.select;

import java.util.EnumMap;

/**
 * @author iumyx
 * @description: 图床服务商选择工厂
 * @date 2024/6/27 9:52
 */
public class SelectorFactory {

    private final static EnumMap<SelectorTypeEnum, Selector> selectorMap = new EnumMap<>(SelectorTypeEnum.class);

    static {
        selectorMap.put(SelectorTypeEnum.POLLING, new PollingLoadSelector());
        selectorMap.put(SelectorTypeEnum.RANDOM, new RandomSelector());
    }

    public static Selector getDefaultInstance() {
        return getInstance(SelectorTypeEnum.RANDOM);
    }

    public static Selector getInstance(SelectorTypeEnum type) {
        return selectorMap.get(type);
    }
}
