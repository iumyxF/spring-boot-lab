package com.example.juc.test_cas_second;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/22 10:34
 */
public class AtomicDemo {

    public static AtomicInteger num = new AtomicInteger(0);

    public void increase() {
        num.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    ad.increase();
                }
            });
        }
        Thread.sleep(1500);
        System.out.println("num = " + num);
        executor.shutdown();
    }
}
