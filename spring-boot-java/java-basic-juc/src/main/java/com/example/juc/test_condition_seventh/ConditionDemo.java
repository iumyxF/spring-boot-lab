package com.example.juc.test_condition_seventh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author iumyxF
 * @description: 线程通信 和Object的wait notify 相似
 * @date 2023/11/23 16:59
 */
public class ConditionDemo {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Clerk clerk = new Clerk(shop);
        Customer c1 = new Customer(shop);
        Customer c2 = new Customer(shop);
        new Thread(clerk, "店员").start();
        new Thread(c1, "顾客甲").start();
        new Thread(c2, "顾客乙").start();
    }
}

class Shop {
    private int product = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void addProduct() {
        lock.lock();
        try {
            while (product >= 1) {
                try {
                    //商品大于1时阻塞等待商品减少
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " : 增加一个商品，" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void deleteProduct() {
        lock.lock();
        try {
            while (product < 1) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " : 购买一个商品，" + --product);
            condition.signalAll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 店员进货商品
 */
class Clerk implements Runnable {

    private final Shop shop;

    public Clerk(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            shop.addProduct();
        }
    }
}

/**
 * 顾客消费商品
 */
class Customer implements Runnable {

    private final Shop shop;

    public Customer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            shop.deleteProduct();
        }
    }
}