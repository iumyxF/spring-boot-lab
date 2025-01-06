package com.example.juc.test_read_write_lock_ninth;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author iumyxF
 * @description: 读写锁 读写互斥、写写互斥，读读不互斥
 * @date 2023/11/30 16:49
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ReadWriteLockTest test = new ReadWriteLockTest();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                test.write((int) (Math.random() * 101));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "write").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                test.read();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "read").start();
    }
}

class ReadWriteLockTest {

    private int number = 0;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ReadWriteLockTest() {
    }

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(int number) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        } finally {
            lock.writeLock().unlock();
        }
    }
}