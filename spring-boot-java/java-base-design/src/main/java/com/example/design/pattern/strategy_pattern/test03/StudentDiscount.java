package com.example.design.pattern.strategy_pattern.test03;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/7 9:48
 */
public class StudentDiscount implements Discount {

    /**
     * 学生价 8折处理
     *
     * @param price 原价
     * @return 结果
     */
    @Override
    public double calculate(double price) {
        System.out.println("学生票：");
        return price * 0.8;
    }
}
