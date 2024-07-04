package com.example.juc.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/4 14:17
 */
public class Demo2 {

    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService service = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("全部到达" + Thread.currentThread().getName() + "呼叫服务员开始点餐！");
            service.shutdown();

        });
        for (int j = 0; j < 5; j++) {
            service.execute(() -> {
                try {
                    Thread.sleep(random.nextInt(5) * 1000);
                    System.out.println(Thread.currentThread().getName() + "同学到达");
                    barrier.await();
                    Thread.sleep(random.nextInt(5) * 1000);
                    System.out.println(Thread.currentThread().getName() + "同学点餐");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
