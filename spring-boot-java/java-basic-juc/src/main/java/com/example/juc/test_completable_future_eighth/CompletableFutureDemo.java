package com.example.juc.test_completable_future_eighth;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/27 17:26
 */
public class CompletableFutureDemo {

    /**
     * 编排任务
     * 第一层：任务1 任务2
     * 第二层：任务3（依赖任务1），任务4（依赖任务2），任务5（既依赖任务1也依赖任务2）
     * 第三层：任务6（依赖任务3、4、5）
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("done task1");
            return "task1";
        }, executor);

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("done task2");
            return "task2";
        }, executor);

        //一元依赖
        CompletableFuture<String> cf3 = cf1.thenApply(result1 -> {
            System.out.println("done task3");
            return "task3";
        });

        CompletableFuture<String> cf4 = cf2.thenApply(result2 -> {
            System.out.println("done task4");
            return "task4";
        });

        //二元依赖
        CompletableFuture<String> cf5 = cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println("done task5");
            return "task5";
        });

        //多元依赖
        //allOf():全部任务完成后执行新的任务
        //anyOf():只要有一个任务完成就执行新任务
        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        CompletableFuture<String> future = cf6.thenApply(v -> {
            String result3 = cf3.join();
            String result4 = cf4.join();
            String result5 = cf5.join();
            return "all result " + "[" + result3 + result4 + result5 + "]";
        });

        String s = future.get();
        System.out.println(s);
    }
}
