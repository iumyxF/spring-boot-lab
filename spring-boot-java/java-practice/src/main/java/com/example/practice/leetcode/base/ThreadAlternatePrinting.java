package com.example.practice.leetcode.base;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fzy
 * @description:
 * @date 2024/12/5 14:29
 */
public class ThreadAlternatePrinting {

    private static int count = 0;

    private static final ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {

        new Thread(new Worker(0, 'A', conditionA, conditionB)).start();
        new Thread(new Worker(1, 'B', conditionB, conditionC)).start();
        new Thread(new Worker(2, 'C', conditionC, conditionA)).start();

    }

    static class Worker implements Runnable {

        private int printCount;
        private char printChar;
        private Condition self;
        private Condition next;

        public Worker(int printCount, char printChar, Condition self, Condition next) {
            this.printCount = printCount;
            this.printChar = printChar;
            this.self = self;
            this.next = next;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    lock.lock();
                    while (count % 3 != printCount) {
                        self.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " : " + printChar);
                    count++;
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