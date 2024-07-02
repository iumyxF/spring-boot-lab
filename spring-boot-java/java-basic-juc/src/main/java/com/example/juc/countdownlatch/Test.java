package com.example.juc.countdownlatch;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author feng
 * @date 2024/4/13 10:37
 */
public class Test {

    /**
     * 交替打印100
     * <p>
     * 线程1：先获取信号量，如果获取成功，输出index，index++，阻塞
     * 线程2：
     *
     * @param args
     */
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        Thread t2 = new Thread(worker);
        t1.start();
        t2.start();
    }
}

class Worker implements Runnable {

    private int num = 0;

    /**
     * 要使用公平锁
     */
    private final ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (num <= 100) {
                    System.out.println();
                    System.out.println(Thread.currentThread().getName() + ":" + num++);
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
