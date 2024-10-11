package com.example.test;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author fzy
 * @description:
 * @date 2024/9/29 14:50
 */
public class TestMain {

    private static final HashMap<String, CountDownLatch> map = new HashMap<>();


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notifyTest("a");
            System.out.println(System.currentTimeMillis());
        }).start();
        map.put("a", countDownLatch);
        System.out.println(System.currentTimeMillis());
        waitTest(countDownLatch);
    }


    public static void waitTest(CountDownLatch countDownLatch) throws InterruptedException {
        System.out.println("阻塞等待中...");
        countDownLatch.await(3L, TimeUnit.SECONDS);
    }

    public static void notifyTest(String key) {
        CountDownLatch countDownLatch = map.get(key);
        countDownLatch.countDown();
        System.out.println(key + " 完成任务");
    }
}
