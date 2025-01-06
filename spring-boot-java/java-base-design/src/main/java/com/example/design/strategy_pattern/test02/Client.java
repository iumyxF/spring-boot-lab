package com.example.design.strategy_pattern.test02;

/**
 * @author iumyxF
 * @description: 启动类
 * @date 2023/6/7 9:45
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        AbstractStrategy strategy;
        //可在运行时指定类型
        strategy = new ConcreteStrategyA();
        context.setStrategy(strategy);
        context.algorithm();
    }
}
