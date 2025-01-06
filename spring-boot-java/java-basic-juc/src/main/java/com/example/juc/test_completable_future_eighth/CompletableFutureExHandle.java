package com.example.juc.test_completable_future_eighth;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author iumyxF
 * @description: CompletableFuture的异常处理
 * @date 2023/11/30 9:50
 */
@Slf4j
public class CompletableFutureExHandle {


    public static void main(String[] args) {
        CompletableFutureExHandle handle = new CompletableFutureExHandle();
        CompletableFuture<Integer> cancelTypeAsync = handle.getCancelTypeAsync2();
        cancelTypeAsync.thenAccept(System.out::println);
    }

    /**
     * 由于异步执行的任务在其他线程上执行，而异常信息存储在线程栈中，
     * 因此当前线程除非阻塞等待返回结果，否则无法通过try\catch捕获异常。
     * CompletableFuture提供了异常捕获回调exceptionally，相当于同步调用中的try\catch
     */
    public CompletableFuture<Integer> getCancelTypeAsync() {
        //业务方法，内部会发起异步rpc调用
        CompletableFuture<Integer> remarkResultFuture = findOrderCancelledRemarkByOrderIdAsync(1);
        //通过exceptionally 捕获异常，打印日志并返回默认值
        return remarkResultFuture.exceptionally(err -> {
            log.error("xxx.xxx Exception orderId = {}", 1);
            return 0;
        });
    }

    /**
     * 有一点需要注意，CompletableFuture在回调方法中对异常进行了包装。
     * 大部分异常会封装成CompletionException后抛出，真正的异常存储在cause属性中，
     * 因此如果调用链中经过了回调方法处理那么就需要用Throwable.getCause()方法提取真正的异常。
     * 但是，有些情况下会直接返回真正的异常，最好使用工具类提取异常
     */
    public CompletableFuture<Integer> getCancelTypeAsync2() {
        CompletableFuture<Integer> remarkResultFuture = findOrderCancelledRemarkByOrderIdAsync(1);
        return remarkResultFuture
                .thenApply(res -> {
                    //这里增加了一个回调方法thenApply，如果发生异常thenApply内部会通过new CompletionException(throwable) 对异常进行包装
                    //这里是一些业务操作
                    return ++res;
                })
                .exceptionally(err -> {
                    //log.error("xxx.xxx Exception orderId = {}", ExceptionUtils.extractRealException(err));
                    return 0;
                });
    }

    /**
     * 模拟第三方调用接口
     */
    public CompletableFuture<Integer> findOrderCancelledRemarkByOrderIdAsync(long orderId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("开始查询订单");
            int i = 10 / 0;
            return 1234567890;
        });
    }

}
