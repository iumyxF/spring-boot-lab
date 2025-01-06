package com.example.design.pattern.observer_pattern.test03;

/**
 * @author iumyxF
 * @description: 观察者
 * @date 2024/1/11 15:34
 */
public interface Observer {

    String getName();

    void setName(String name);

    /**
     * 抄底通知
     */
    void bargainHunting();
}
