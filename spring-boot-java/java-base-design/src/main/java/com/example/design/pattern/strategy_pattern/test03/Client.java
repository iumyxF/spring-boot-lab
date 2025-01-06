package com.example.design.pattern.strategy_pattern.test03;

/**
 * @author iumyxF
 * @description:
 * @date 2023/6/7 9:52
 */
public class Client {

    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;
        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");
        /*
         * 这里获取Discount的具体实现可以通过数据库或者XML其他动态判断方式
         * 例如，假设从数据库读取年龄，是否是会员等信息...判断用户是属于什么类型，再创建对应的Discount对象
         * 如果不想用if-else的方式来判断的话，可以使用map的方式。
         */
        Discount discount = new StudentDiscount();
        //注入折扣对象
        mt.setDiscount(discount);
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
