package com.example.juc.countdownlatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description: 实现一个容器，提供两个方法，add，size 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束.
 * 使用wait和notify实现
 * @date 2023/7/20 10:23
 */
public class CountDownDemo1 {

    private final List<Integer> list = new ArrayList<>();

    public void add(int i) {
        list.add(i);
    }

    public int getSize() {
        return list.size();
    }

    public static void main(String[] args) {
        CountDownDemo1 demo1 = new CountDownDemo1();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("demo1 启动");
                if (demo1.getSize() != 5) {
                    try {
                        //一开始就会阻塞，等待下面线程lock.notify();时才会继续执行
                        lock.wait();
                        System.out.println("demo1 结束");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.notify();
            }
        }, "demo1").start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 10; i++) {
                    demo1.add(i);
                    System.out.println("add " + i);
                    if (demo1.getSize() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();
    }
}
