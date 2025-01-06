package com.example.design.pattern.observer_pattern.test03;

import java.util.ArrayList;

/**
 * @author iumyxF
 * @description: 股票机构
 * @date 2024/1/11 15:42
 */
public abstract class StockInstitutions {

    protected final ArrayList<Observer> suckers = new ArrayList<>();

    public void join(Observer observer) {
        System.out.println("韭菜:" + observer.getName() + "进场了");
        suckers.add(observer);
    }

    public void quit(Observer observer) {
        System.out.println("韭菜:" + observer.getName() + "破产了");
        suckers.remove(observer);
    }

    public abstract void change();
}
