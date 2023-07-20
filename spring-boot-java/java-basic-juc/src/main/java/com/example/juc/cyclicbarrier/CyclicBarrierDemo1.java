package com.example.juc.cyclicbarrier;


import java.util.Random;
import java.util.concurrent.*;

/**
 * @author fzy
 * @description :CyclicBarrier字面意思回环屏障，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * @date 2023/7/20 11:15
 */
public class CyclicBarrierDemo1 {

    /**
     * 参数parties指让多少个线程或者任务等待至barrier状态
     * 参数barrierAction为当这些线程都达到barrier状态时会执行的内容
     * public CyclicBarrier(int parties, Runnable barrierAction) {}
     * public CyclicBarrier(int parties) {}
     * <p>
     * 然后CyclicBarrier中最重要的方法就是 await 方法，它有2个重载版本：
     * 第一个版本比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
     * 第二个版本是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。
     * public int await() throws InterruptedException, BrokenBarrierException { };
     * public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
     */
    public static void main(String[] args) {
        int n = 4;
        //CyclicBarrier barrier = new CyclicBarrier(n);
        //如果说想在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数：
        CyclicBarrier barrier = new CyclicBarrier(n, () -> System.out.println(Thread.currentThread()));
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < n; ++i) {
            exec.execute(new WriteTask(barrier));
        }
        exec.shutdown();
    }
}

class WriteTask implements Runnable {

    private static int count = 0;
    private final int id = count++;
    private final CyclicBarrier barrier;
    private static final Random random = new Random(47);

    public WriteTask(CyclicBarrier cyclicBarrier) {
        this.barrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(this + "开始写入数据...");
        try {
            // 以睡眠来模拟写入数据操作
            TimeUnit.MILLISECONDS.sleep(random.nextInt(5000));
            System.out.println(this + "写入数据完毕，等待其他线程写入完毕" + " " + System.currentTimeMillis());
            //所有线程任务都会在这里阻塞，等到barrier为0了才能继续向下执行
            barrier.await();
        } catch (InterruptedException e) {
            System.out.println(this + "is interrupted!");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("所有任务写入完毕，继续处理其他任务... " + System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + id;
    }
}