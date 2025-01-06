package com.example.design.pattern.observer_pattern.test03;

/**
 * @author iumyxF
 * @description: 股民（韭菜）
 * @date 2024/1/11 15:48
 */
public class sucker implements Observer {

    String name;

    public sucker(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void bargainHunting() {
        System.out.println("韭菜: " + name + " 要抄底了");
    }
}
