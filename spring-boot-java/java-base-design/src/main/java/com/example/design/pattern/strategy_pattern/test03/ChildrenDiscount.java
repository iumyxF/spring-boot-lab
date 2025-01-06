package com.example.design.pattern.strategy_pattern.test03;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/7 9:49
 */
public class ChildrenDiscount implements Discount {

    /**
     * 儿童票
     *
     * @param price 原价
     * @return 结果
     */
    @Override
    public double calculate(double price) {
        System.out.println("儿童票：");
        return price - 10;
    }
}
