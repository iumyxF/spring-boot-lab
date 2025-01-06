package com.example.design.observer_pattern.test02;

/**
 * @author iumyxF
 * @description: 观察者
 * @date 2024/1/11 14:12
 */
interface Observer {

    String getName();

    void setName(String name);

    /**
     * 增援
     */
    void help();

    /**
     * 通知
     * 声明遭受攻击方法
     */
    void beAttacked(AllyControlCenter acc);
}
