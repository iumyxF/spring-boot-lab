package com.example.juc.semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author iumyxF
 * @description: Semaphore翻译成字面意思为 信号量，Semaphore 可以同时让多个线程同时访问共享资源，
 * 通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * @date 2023/7/20 11:38
 */
public class SemaphoreDemo1 {
    /**
     * 构造方法：
     * 参数permits表示许可数目，即同时可以允许多少线程进行访问
     * public Semaphore(int permits) {
     * sync = new NonfairSync(permits);
     * }
     * <p>
     * 这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
     * public Semaphore(int permits, boolean fair) {
     * sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
     * }
     * <p>
     * 重要方法：
     * 阻塞方法：
     * public void acquire() throws InterruptedException {  }     //获取一个许可
     * public void acquire(int permits) throws InterruptedException { }    //获取permits个许可
     * public void release() {}          //释放一个许可
     * public void release(int permits) {}    //释放permits个许可
     * acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
     * release()用来释放许可。
     * 注意，在释放许可之前，必须先获获得许可。这4个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法
     * <p>
     * 非阻塞方法：
     * 尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
     * public boolean tryAcquire() { };
     * 尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
     * public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };
     * 尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
     * public boolean tryAcquire(int permits) { };
     * 尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
     * public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { };
     */
    public static void main(String[] args) {
        //工人数
        int n = 8;
        //机器数目
        Semaphore semaphore = new Semaphore(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < n; ++i) {
            exec.execute(new Worker(semaphore));
        }
        exec.shutdown();
    }
}

class Worker implements Runnable {

    private static int count = 0;
    private final int id = count++;
    private int finished = 0;
    private Random random = new Random(47);
    private Semaphore semaphore;

    public Worker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                semaphore.acquire();
                System.out.println(this + " 占用一个机器在生产...   ");
                TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
                synchronized (this) {
                    System.out.println(" 已经生产了" + (++finished) + "个产品," + "释放出机器");
                }
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "-" + id;
    }
}