package com.example.design.strategy_pattern.test03;

/**
 * @author iumyxF
 * @description: context环境类
 * @date 2023/6/7 9:47
 */
public class MovieTicket {

    private double price;

    /**
     * 维持一个对抽象折扣类的引用
     */
    private Discount discount;

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * change:2023.8.3
     * 可以使用注入方式，也可以使用这种构造方法方式
     */
    public MovieTicket() {
        this.discount = DiscountFactory.getDiscount(StudentDiscount.class.getName());
    }

    /**
     * 注入一个折扣类对象
     *
     * @param discount 折扣类对象
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getPrice() {
        //调用折扣类的折扣价计算方法
        return discount.calculate(this.price);
    }
}
