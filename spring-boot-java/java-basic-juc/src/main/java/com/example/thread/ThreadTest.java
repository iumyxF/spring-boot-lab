package com.example.thread;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/1 9:06
 */
public class ThreadTest {
    /*
    核心线程数：线程池的基本大小，即即使没有任务需要执行，线程池也会保持的线程数。
    最大线程数：线程池允许的最大线程数。当工作队列满时，线程池会创建新的线程来处理任务，直到达到这个数值。
    队列容量：用于存储待执行任务的队列。ThreadPoolExecutor支持多种类型的队列，如ArrayBlockingQueue、LinkedBlockingQueue等。
    空闲线程存活时间：当线程池中的线程数量超过核心线程数时，这是多余空闲线程在终止前等待新任务的最长时间。
    时间单位：指定空闲线程存活时间的单位。
    线程工厂：用于创建新线程的工厂。你可以自定义线程工厂，为线程设置名称、优先级等属性。
    拒绝策略：当工作队列满且线程池中的线程数量达到最大线程数时，新提交的任务将被拒绝。你可以自定义拒绝策略，如抛出异常、丢弃任务、丢弃最老的任务等。
     */

    /*
    线程分为两类：用户线程和守护线程，默认情况下我们创建的线程或线程池都是用户线程，所以用户线程也被称之为普通线程。
    Thread.isDaemon()
    main方法的线程也是用户线程
    <p>
    守护线程定义:
    守护线程(Daemon Thread)也被称之为后台线程或服务线程，守护线程是为用户线程服务的，
    (重点) - 当程序中的用户线程全部执行结束之后，守护线程也会跟随结束。
    守护线程的角色就像“服务员”，而用户线程的角色就像“顾客”，当“顾客”全部走了之后(全部执行结束)，
    那“服务员”(守护线程)也就没有了存在的意义，所以当一个程序中的全部用户线程都结束执行之后，
    那么无论守护线程是否还在工作都会随着用户线程一块结束，整个程序也会随之结束运行。
     */
    public void createThreadPool() {
        // 创建一个有界队列，容量为10
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                // 核心线程数
                2,
                // 最大线程数
                5,
                // 空闲线程存活时间
                60L,
                // 时间单位
                TimeUnit.SECONDS,
                // 工作队列
                queue,
                // 线程工厂
                new NamedThreadFactory("my-test-thread", false),
                // 拒绝策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
