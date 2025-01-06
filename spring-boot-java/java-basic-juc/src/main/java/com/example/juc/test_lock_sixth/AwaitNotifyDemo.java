package com.example.juc.test_lock_sixth;

/**
 * @author iumyxF
 * @description: notify() 和 notifyAll() 区别
 * notify() 随机通知一个等待的线程
 * notifyAll() 通知所有等待的线程
 * @date 2023/11/22 15:38
 */
public class AwaitNotifyDemo {

    public static void main(String[] args) {
        ConvenienceStore store = new ConvenienceStore();

        new Thread(new Consumer(store), "顾客1").start();
        //new Thread(new Consumer(store), "顾客2").start();
        new Thread(new Producer(store), "店员1").start();
    }
}

/**
 * 便利店
 */
class ConvenienceStore {

    private int colaCount;

    public synchronized void sale() throws InterruptedException {
        while (colaCount <= 0) {
            //System.out.println("商品库存不足，等待补货...");
            this.wait();
        }
        colaCount--;
        System.out.println(Thread.currentThread().getName() + " => 买了一瓶可乐");
        this.notify();
    }

    public synchronized void purchaseGoods() throws InterruptedException {
        while (colaCount > 0) {
            //System.out.println("商品库存充足，等待出售...");
            this.wait();
        }
        colaCount++;
        System.out.println(Thread.currentThread().getName() + " => 进货一瓶可乐");
        this.notify();
    }
}

class Consumer implements Runnable {

    private final ConvenienceStore store;

    public Consumer(ConvenienceStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                store.sale();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class Producer implements Runnable {
    private final ConvenienceStore store;

    public Producer(ConvenienceStore store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                store.purchaseGoods();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
