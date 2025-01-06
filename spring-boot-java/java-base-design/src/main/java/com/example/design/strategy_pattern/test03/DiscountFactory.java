package com.example.design.strategy_pattern.test03;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author iumyxF
 * @description: 工厂 + 单例模式
 * @date 2023/8/3 9:07
 */
public class DiscountFactory {

    /**
     * className 和 折扣类型映射
     */
    private static final Map<String, Discount> DISCOUNT_MAP = new ConcurrentHashMap<>();

    private DiscountFactory() {
    }

    public static Discount getDiscount(String className) {
        Discount discount = DISCOUNT_MAP.get(className);
        if (null == discount) {
            synchronized (className.intern()) {
                discount = DISCOUNT_MAP.computeIfAbsent(className, key -> {
                    try {
                        return (Discount) Class.forName(className).newInstance();
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        return discount;
    }
}
