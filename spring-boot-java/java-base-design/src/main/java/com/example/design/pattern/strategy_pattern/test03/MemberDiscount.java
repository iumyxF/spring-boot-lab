package com.example.design.pattern.strategy_pattern.test03;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/7 9:49
 */
public class MemberDiscount implements Discount {

    /**
     * 会员价
     *
     * @param price 原价
     * @return 结果
     */
    @Override
    public double calculate(double price) {
        System.out.println("VIP会员票：");
        System.out.println("增加积分！");
        return price * 0.5;
    }
}
