package com.example.practice.leetcode.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fzy
 * @description: 交替打印123 ABC
 * @date 2024/12/5 16:47
 */
public class ThreadAlternatePrintingTest {

    private static int num = 0;
    private static int workCount = 3;
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        new Thread(new Worker('A', 0, a, b)).start();
        new Thread(new Worker('B', 1, b, c)).start();
        new Thread(new Worker('C', 2, c, a)).start();
    }

    static class Worker implements Runnable {

        private char printContent;
        private int printCount;
        private Condition self;
        private Condition next;

        public Worker(char printContent, int printCount, Condition self, Condition next) {
            this.printContent = printContent;
            this.printCount = printCount;
            this.self = self;
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    lock.lock();
                    while (num % workCount != printCount) {
                        self.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + printContent);
                    num++;
                    next.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
