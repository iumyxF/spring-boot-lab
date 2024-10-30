package com.example.practice.leetcode.test.sync;

/**
 * @author fzy
 * @description:
 * @date 2024/10/25 10:38
 */
public class SyncTest {

    public void update(String meetingKey) throws InterruptedException {
        synchronized (meetingKey.intern()) {
            System.out.println("meetingKey = " + meetingKey);
            Thread.sleep(5 * 1000L);
        }
        System.out.println(meetingKey + " end ..");
    }


    public static void main(String[] args) throws InterruptedException {
        SyncTest test = new SyncTest();
        new Thread(()->{
            try {
                test.update("m1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"T1").start();

        new Thread(()->{
            try {
                test.update("m2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"T2").start();

        new Thread(()->{
            try {
                test.update("m3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"T3").start();

        new Thread(()->{
            try {
                test.update("m1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"T4").start();

        Thread.currentThread().join();
    }

}
