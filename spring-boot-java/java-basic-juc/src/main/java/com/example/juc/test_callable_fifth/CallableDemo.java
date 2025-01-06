package com.example.juc.test_callable_fifth;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author iumyxF
 * @description: callable接口的使用
 * @date 2023/11/22 15:02
 */
public class CallableDemo {

    public static void main(String[] args) {
        CallableThread thread = new CallableThread();
        FutureTask<Integer> futureTask = new FutureTask<>(thread);
        //启动线程
        new Thread(futureTask).start();
        try {
            Integer sum = futureTask.get();
            System.out.println("sum = " + sum);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

class CallableThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("task start...");
        int sum = 0;
        int taskTime = new Random().nextInt(10) * 1000;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        //模拟执行任务的时间
        Thread.sleep(taskTime);
        System.out.println("task end...");
        return sum;
    }
}