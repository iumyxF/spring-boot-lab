package com.example.practice.leetcode.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fzy
 * @description:
 * @date 2024/12/25 15:17
 */
public class ProducerConsumer {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final int[] buffer;
    private int count;
    private int putIndex;
    private int takeIndex;

    public ProducerConsumer(int size) {
        buffer = new int[size];
        count = 0;
        putIndex = 0;
        takeIndex = 0;
    }

    public void put(int value) throws InterruptedException {
        lock.lock();
        try {
            while (count == buffer.length) {
                notFull.await();
            }
            buffer[putIndex] = value;
            putIndex = (putIndex + 1) % buffer.length;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            int value = buffer[takeIndex];
            takeIndex = (takeIndex + 1) % buffer.length;
            count--;
            notFull.signal();
            return value;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        int bufferSize = 5;
        ProducerConsumer producerConsumer = new ProducerConsumer(bufferSize);

        // 创建生产者线程
        Thread producerThread1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    producerConsumer.put(i);
                    System.out.println("生产者1生产: " + i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread producerThread2 = new Thread(() -> {
            try {
                for (int i = 11; i <= 20; i++) {
                    producerConsumer.put(i);
                    System.out.println("生产者2生产: " + i);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 创建消费者线程
        Thread consumerThread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = producerConsumer.take();
                    System.out.println("消费者1消费: " + value);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    int value = producerConsumer.take();
                    System.out.println("消费者2消费: " + value);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread1.start();
        producerThread2.start();
        consumerThread1.start();
        consumerThread2.start();

        try {
            producerThread1.join();
            producerThread2.join();
            consumerThread1.join();
            consumerThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("测试结束");
    }
}
