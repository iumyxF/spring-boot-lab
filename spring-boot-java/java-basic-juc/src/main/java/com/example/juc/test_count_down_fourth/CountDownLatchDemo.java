package com.example.juc.test_count_down_fourth;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author iumyxF
 * @description: countDownLatch 的使用
 * 使用场景：多个子任务需要一起到达某个时间点后，主线程才能继续执行
 * 配合springBoot中时使用时，可以在某个业务方法内new CountDownLatch 然后作为参数传递给子线程方法
 * @date 2023/11/22 14:09
 */
public class CountDownLatchDemo {

    private static final Map<String, Integer> MAP = new ConcurrentHashMap<>(4);

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(4);

    /**
     * 模拟多线程执行多个任务，最后获取统计结果
     * <p>
     * 使用callable也可以实现相同效果callable的get()方法是阻塞等待的
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Thread firstThread = new Thread(() -> {
            try {
                int random = new Random().nextInt(10) * 1000;
                System.out.println("执行任务1...");
                Thread.sleep(random);
                MAP.put("任务1", random);
                System.out.println("任务1执行完毕...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                COUNT_DOWN_LATCH.countDown();
            }
        });

        Thread secondThread = new Thread(() -> {
            try {
                int random = new Random().nextInt(10) * 1000;
                System.out.println("执行任务2...");
                Thread.sleep(random);
                MAP.put("任务2", random);
                System.out.println("任务2执行完毕...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                COUNT_DOWN_LATCH.countDown();
            }
        });

        Thread thirdThread = new Thread(() -> {
            try {
                int random = new Random().nextInt(10) * 1000;
                System.out.println("执行任务3...");
                Thread.sleep(random);
                MAP.put("任务3", random);
                System.out.println("任务3执行完毕...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                COUNT_DOWN_LATCH.countDown();
            }
        });

        Thread fourthThread = new Thread(() -> {
            try {
                int random = new Random().nextInt(10) * 1000;
                System.out.println("执行任务4...");
                Thread.sleep(random);
                MAP.put("任务4", random);
                System.out.println("任务4执行完毕...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                COUNT_DOWN_LATCH.countDown();
            }
        });

        //启动所有子线程任务
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();

        try {
            COUNT_DOWN_LATCH.await();
            long endTime = System.currentTimeMillis();
            System.out.println("------任务全部完成--------");
            System.out.println("任务结果：" + MAP);
            System.out.println("任务总执行时间为" + (endTime - startTime) / 1000 + "秒");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
