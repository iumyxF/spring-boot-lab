package com.example.juc.test_volatile_first;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author iumyxF
 * @description: volatile 不能保证原子性
 * @date 2023/11/22 10:20
 */
public class VolatileDemo2 {

    public static volatile int num = 0;

    public void increase() {
        num++;
    }

    /**
     * 实验：开启五个线程分别对执行num+1
     * 预期结果：num = 2500
     * 运行结果：num 总小于2500，说明volatile没有原子性
     * <p>
     * inc++:分为三步。volatile 是无法保证这三个操作是具有原子性的
     * 1.读取 inc 的值。
     * 2.对 inc 加 1。
     * 3.将 inc 的值写回内存。
     * <p>
     * 如果想实现同步
     * 1.increase()方法添加synchronized
     * 2.num类型修改成AtomicXxx类型
     * 3.使用ReentrantLock
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        VolatileDemo2 vd = new VolatileDemo2();
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    vd.increase();
                }
            });
        }
        Thread.sleep(1500);
        System.out.println("num = " + num);
        executor.shutdown();
    }
}
