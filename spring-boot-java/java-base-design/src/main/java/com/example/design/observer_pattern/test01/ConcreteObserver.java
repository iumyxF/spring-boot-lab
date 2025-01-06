package com.example.design.observer_pattern.test01;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/11 14:07
 */
public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println("观察者接受到通知");
    }
}
