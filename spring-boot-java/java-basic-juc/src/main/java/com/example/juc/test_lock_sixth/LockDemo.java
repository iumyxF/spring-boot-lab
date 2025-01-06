package com.example.juc.test_lock_sixth;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/22 15:25
 */
public class LockDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "2号窗").start();
        new Thread(ticket, "1号窗").start();
        new Thread(ticket, "3号窗").start();
    }
}

class Ticket implements Runnable {

    private int total = 100;

    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (total > 0) {
                    try {
                        Thread.sleep(200);
                        System.out.println(Thread.currentThread().getName() + "售出一张票, 剩余:" + --total);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
