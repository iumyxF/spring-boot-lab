package com.example.juc.countdownlatch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author iumyxF
 * @description:
 * @date 2023/7/20 10:23
 */
public class CountDownDemo3 {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new CountDownDemo3Task(latch));
        exec.execute(new CountDownDemo3Task(latch));

        try {
            System.out.println("等待2个子线程执行完毕...");
            long start = System.currentTimeMillis();
            //阻塞等待
            latch.await();
            long end = System.currentTimeMillis();

            System.out.println("2个子线程已经执行完毕，耗时：" + (end - start));
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            System.out.println("主线程被中断");
        }
        exec.shutdown();
    }
}

/**
 * 自定义线程
 */
class CountDownDemo3Task implements Runnable {

    private static int count = 0;
    private final int id = count++;
    final CountDownLatch latch;

    public CountDownDemo3Task(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + "正在执行");
            TimeUnit.MILLISECONDS.sleep(3000);
            System.out.println(this + "执行完毕");
            //减一
            latch.countDown();
        } catch (InterruptedException e) {
            System.out.println(this + " 被中断");
        }
    }

    @Override
    public String toString() {
        return "Task-" + id;
    }
}
