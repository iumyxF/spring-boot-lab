package com.example.design.pattern.observer_pattern.test03;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/11 16:01
 */
public class Demo {

    /**
     * 练习：
     * 当股票购买者所购买的某支股票价格变化幅度达到5%时，
     * 系统将自动发送通知（包括新价格）给购买该股票的所有股民。
     * 试使用观察者模式设计并实现该系统。
     */
    public static void main(String[] args) {
        StockInstitutions wallStreet = new WallStreet();
        sucker s1 = new sucker("莫菲特");
        wallStreet.join(s1);
        sucker s2 = new sucker("索罗斯");
        wallStreet.join(s2);
        sucker s3 = new sucker("罗杰斯");
        wallStreet.join(s3);
        sucker s4 = new sucker("彼得·林奇");
        wallStreet.join(s4);

        //技术性调整
        wallStreet.change();
    }
}
