package com.example.design.strategy_pattern.test02;

/**
 * @author iumyxF
 * @description: 具体实现
 * @date 2023/6/7 9:44
 */
public class ConcreteStrategyA extends AbstractStrategy {

    /**
     * 算法实现A
     */
    @Override
    public void algorithm() {
        System.out.println("ConcreteStrategyA algorithm working ...");
    }
}
