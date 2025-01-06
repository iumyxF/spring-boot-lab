package com.example.design.strategy_pattern.test02;

/**
 * @author iumyxF
 * @description: 环境类
 * @date 2023/6/7 9:44
 */
public class Context {
    /**
     * 维持一个对抽象策略类的引用
     */
    private AbstractStrategy strategy;

    public void setStrategy(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 调用策略类中的算法
     */
    public void algorithm() {
        strategy.algorithm();
    }
}
