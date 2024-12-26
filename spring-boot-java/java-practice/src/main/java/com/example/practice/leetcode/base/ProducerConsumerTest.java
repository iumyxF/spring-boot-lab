package com.example.practice.leetcode.base;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fzy
 * @description:
 * @date 2024/12/26 15:41
 */
public class ProducerConsumerTest {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition prodCon = lock.newCondition();
    private final Condition consCon = lock.newCondition();
    private final LinkedList<Integer> list = new LinkedList<>();
    private final int maxCont;
    private int count;

    public ProducerConsumerTest(int size) {
        this.maxCont = size;
        this.count = 0;
    }

    public void produce(int value) {
        lock.lock();
        try {
            while (count == maxCont) {
                // 阻塞生产者
                prodCon.await();
            }
            list.addLast(value);
            count++;
            // 唤醒消费者
            consCon.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (count == 0) {
                // 阻塞消费者
                consCon.await();
            }
            list.pollFirst();
            count--;
            // 唤醒生产者
            prodCon.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        ProducerConsumerTest pct = new ProducerConsumerTest(5);
        Thread prodThread = new Thread(() -> {
            try {
                for (int i = 1; i <= size; i++) {
                    pct.produce(i);
                    System.out.println("生产: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consThread = new Thread(() -> {
            try {
                for (int i = 1; i <= size; i++) {
                    pct.consume();
                    System.out.println("消费: " + i);
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        prodThread.start();
        consThread.start();

        prodThread.join();
        consThread.join();
    }
}
