package com.example.design.strategy_pattern.test01;

/**
 * @author iumyxF
 * @description: 未使用策略模式
 * @date 2023/6/7 9:33
 */
public class MovieTicket {

    private static final String STUDENT_TYPE = "student";
    private static final String CHILDREN_TYPE = "children";
    private static final String VIP_TYPE = "vip";
    private static final Integer STUDENT_LIMIT_PRICE = 20;

    /**
     * 电影票价格
     */
    private double price;

    /**
     * 电影票类型
     */
    private String type;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return this.calculate();
    }

    /**
     * 计算打折之后的票价
     *
     * @return 价格
     */
    public double calculate() {
        //学生票折后票价计算
        if (STUDENT_TYPE.equalsIgnoreCase(this.type)) {
            System.out.println("学生票：");
            return this.price * 0.8;
        }
        //儿童票折后票价计算
        else if (CHILDREN_TYPE.equalsIgnoreCase(this.type) && STUDENT_LIMIT_PRICE <= this.price) {
            System.out.println("儿童票：");
            return this.price - 10;
        }
        //VIP票折后票价计算
        else if (VIP_TYPE.equalsIgnoreCase(this.type)) {
            System.out.println("VIP票：");
            System.out.println("增加积分！");
            return this.price * 0.5;
        } else {
            //如果不满足任何打折要求，则返回原始票价
            return this.price;
        }
    }
}
