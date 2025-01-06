package com.example.design.strategy_pattern.test03;

/**
 * @author iumyxF
 * @description: 折扣类：抽象策略类
 * @date 2023/6/7 9:47
 */
public interface Discount {

    /**
     * 计算价格
     *
     * @param price 原价
     * @return 结果
     */
    double calculate(double price);
}
