package com.example.juc.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author fzy
 * @description: 实现一个容器，提供两个方法，add，size 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束.
 * 使用CountDownLatch 实现
 * @date 2023/7/20 10:23
 */
public class CountDownDemo2 {

    private final List<Integer> list = new ArrayList<>();

    public void add(int i) {
        list.add(i);
    }

    public int getSize() {
        return list.size();
    }

    /**
     * 参数count为计数值
     * public CountDownLatch(int count) {  };
     * <p>
     * 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
     * public void await() throws InterruptedException { };
     * <p>
     * 和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
     * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
     * <p>
     * 将count值减1
     * public void countDown() { };
     */
    public static void main(String[] args) {
        CountDownDemo2 demo2 = new CountDownDemo2();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("线程1 启动");
            if (demo2.getSize() != 5) {
                try {
                    countDownLatch.await();
                    System.out.println("线程1 结束");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "线程1").start();

        new Thread(() -> {
            System.out.println("线程2 启动");
            for (int i = 1; i <= 10; i++) {
                demo2.add(i);
                System.out.println("add " + i);
                if (demo2.getSize() == 5) {
                    System.out.println("countDownLatch open");
                    countDownLatch.countDown();
                }
            }
        }, "线程2").start();
    }
}
