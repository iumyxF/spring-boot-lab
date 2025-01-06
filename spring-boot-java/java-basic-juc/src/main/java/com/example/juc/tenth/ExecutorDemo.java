package com.example.juc.tenth;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author iumyxF
 * @description: 线程池
 * @date 2023/12/1 10:48
 */
public class ExecutorDemo {

    /*
    Executors工具类：线程池不使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样 的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
    1.newFixedThreadPool(int nThreads)：创建一个固定大小的线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    2.newCachedThreadPool()：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
    3.newSingleThreadExecutor()：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
    4.newScheduledThreadPool(int corePoolSize)：创建一个定长线程池，支持定时及周期性任务执行。
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executor = createThreadPool();
        executor.execute(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        });
    }

    /**
     * 线程池7个参数：
     * 1.corePoolSize：核心线程数，表示线程池维护的线程数的下限，当线程池所在线程数小于corePoolSize时，线程池会新建线程来执行任务。
     * 2.maximumPoolSize：最大线程数，表示线程池维护的线程数的上限，当任务队列和线程数达到最大数时，线程池会拒绝新的任务。
     * 3.workQueue：任务队列，用于保存等待执行的任务
     * 4.keepAliveTime：线程存活时间，表示线程池中的线程在corePoolSize与maximumPoolSize之间时的存活时间，单位为TimeUnit。
     * 5.unit：线程存活时间的单位。 workThreadFactory：线程工厂，用于创建线程。
     * 6.handler:
     * 6.1 AbortPolicy:任务队列满了就丢弃并抛出异常
     * 6.2 DiscardPolicy:任务队列满了就丢弃，不抛异常
     * 6.3 DiscardOldestPolicy:这个策略从字面上也很好理解，丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。 因为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队。
     * 6.4 CallerRunsPolicy:这个策略是直接在调用者线程中运行，也就是说当线程池的线程数大于corePoolSize时，如果执行的任务数超过maximumPoolSize，那么多余的任务会交给调用者线程来执行。
     * 6.5 可以自定义拒绝策略：实现RejectedExecutionHandler接口
     */
    public static ThreadPoolExecutor createThreadPool() {
        return new ThreadPoolExecutor(
                1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    /*
     * 线程池的线程执行规则跟:
     * 1.如果线程数量 <= 核心线程数量：直接启动一个核心线程来执行任务，不会放入队列中
     * 2.如果线程数量 线程数量 > 核心线程数量
     *      2.1任务队列是LinkedBlockingQueue
     *          - 无大小限制：超过核心线程数量的任务会放在任务队列中排队。设置的最大线程数无效。线程数最多不会超过核心线程数。
     *          - 大小有限：如果队列没满，会加入队列，如果塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会调用handler。
     *      2.2任务队列是SynchronousQueue（一种无缓冲的等待队列，缓存值为1的阻塞队列）
     *          - 线程池会创建新线程（非核心）执行任务，在任务完成后，闲置时间达到了超时时间就会被清除。当任务数量超过最大线程数时会直接调用handler。
     */
}
