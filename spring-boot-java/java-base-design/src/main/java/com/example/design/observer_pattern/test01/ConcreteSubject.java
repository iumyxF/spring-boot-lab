package com.example.design.observer_pattern.test01;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/11 14:06
 */
public class ConcreteSubject extends Subject {
    @Override
    public void inform() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
