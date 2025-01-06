package com.example.design.pattern.observer_pattern.test01;

import java.util.ArrayList;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/11 13:58
 */
public abstract class Subject {

    /**
     * 定义一个观察者集合用于存储所有观察者对象
     */
    protected final ArrayList<Observer> observers = new ArrayList<>();

    /**
     * 注册方法，用户向观察者列表中添加元素
     *
     * @param observer 观察者对象
     */
    public void add(Observer observer) {
        observers.add(observer);
    }

    /**
     * 注销方法
     *
     * @param observer 观察者对象
     */
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    /**
     * 抽象通知方法
     */
    public abstract void inform();
}
