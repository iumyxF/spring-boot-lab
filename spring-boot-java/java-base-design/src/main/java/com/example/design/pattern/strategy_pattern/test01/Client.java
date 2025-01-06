package com.example.design.pattern.strategy_pattern.test01;

/**
 * @author iumyxF
 * @description: 启动类
 * @date 2023/6/7 9:35
 */
public class Client {

    public static void main(String[] args) {
        MovieTicket mt = new MovieTicket();
        //原始票价
        double originalPrice = 60.0;
        //折后价
        double currentPrice;
        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");
        //学生票
        mt.setType("student");
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
        System.out.println("---------------------------------");
        //儿童票
        mt.setType("children");
        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);
    }
}
