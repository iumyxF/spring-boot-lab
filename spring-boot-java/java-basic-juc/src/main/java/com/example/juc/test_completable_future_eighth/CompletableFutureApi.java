package com.example.juc.test_completable_future_eighth;

import com.example.juc.test_completable_future_eighth.supplier.MySupplier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author iumyxF
 * @description: CompletableFuture api
 * @date 2023/11/28 15:15
 */
public class CompletableFutureApi {

    public static void main(String[] args) {
        CompletableFutureApi api = new CompletableFutureApi();
        //api.instantiateCompletableFuture();

        //api.getResult();
        //api.allOfTest();

        //api.completeTest();
        //api.handleTest();
        //api.applyTest();
        //api.acceptTest();
        //api.exceptionallyTest();

        //api.thenComposeTest();
        //api.thenCombineTest();

        //api.myTest1();
        api.myTest2();
    }

    //region api

    //region create

    /**
     * 实例化CompletableFuture的方法
     * 1.supplyAsync:有返回值executorService
     * 2.runAsync:无返回值
     * 其中supplyAsync用于有返回值的任务，runAsync则用于没有返回值的任务。
     * Executor参数可以手动指定线程池，否则默认ForkJoinPool.commonPool()系统级公共线程池，
     * 注意：这些线程都是Daemon线程，主线程结束Daemon线程不结束，只有JVM关闭时，生命周期终止。
     */
    public void instantiateCompletableFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");
            return "result1";
        }, executorService);
        CompletableFuture<Void> cf2 = CompletableFuture.runAsync(() -> System.out.println("task2 do something..."), executorService);
    }

    //endregion

    //region getResult

    /**
     * 获取CompletableFuture结果的方法
     * 1.get():阻塞到结果返回
     * 2.get(long timeout, TimeUnit unit):阻塞到超时
     * 3.getNow(T valueIfAbsent):获取当前时刻的结果，如果completableFuture没有执行完毕或者结果为空，则返回valueIfAbsent的值
     * 4.join():join() 与get() 区别在于join() 返回计算的结果或者抛出一个unchecked异常(CompletionException)，而get() 返回一个具体的异常
     */
    public void getResult() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");

            //int i = 10 / 0;

            //try {
            //    Thread.sleep(2000);
            //} catch (InterruptedException e) {
            //    throw new RuntimeException(e);
            //}

            return "result1";
        }, executorService);

        //返回具体的异常
        //try {
        //    cf1.get();
        //} catch (InterruptedException | ExecutionException e) {
        //    throw new RuntimeException(e);
        //}

        //Exception in thread "main" java.lang.RuntimeException
        //Caused by: java.util.concurrent.ExecutionException
        //try {
        //    cf1.get(1, TimeUnit.SECONDS);
        //} catch (InterruptedException | ExecutionException | TimeoutException e) {
        //    throw new RuntimeException(e);
        //}

        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
        //String res = cf1.getNow("Zoe");
        //System.out.println(res);

        //Exception in thread "main" java.util.concurrent.CompletionException
        //String join = cf1.join();
        //System.out.println(join);
    }

    /**
     * 等待多个CompletableFuture完成在执行下一步
     */
    public void allOfTest() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                //使用sleep()模拟耗时操作
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture.allOf(future1, future1);
        // 输出3
        System.out.println(future1.join() + future2.join());
    }

    //endregion

    //region 计算完毕后的操作

    /**
     * 计算完毕后的操作1
     * 1.whenComplete
     * 2.whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
     * 3.whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
     * 4.exceptionally:捕获中间产生的异常
     * 方法1和2的区别在于是否使用异步处理
     * 2和3的区别在于是否使用自定义的线程池，
     * 前三个方法都会提供一个返回结果和可抛出异常，我们可以使用lambda表达式的来接收这两个参数，然后自己处理。
     * 方法4，接收一个可抛出的异常，且必须return一个返回值
     */
    public void completeTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");
            //throw new RuntimeException("ex");
            return 666;
        }, executorService);

        CompletableFuture<Integer> future = cf1.whenComplete((res, error) -> {
            System.out.println("task1 result = " + res);
            error.printStackTrace();
        });
    }

    public void exceptionallyTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");
            return null;
        }, executorService);

        CompletableFuture<String> future = cf1.thenApply(res -> {
            //制造空指针异常
            return (int) res;
        }).thenApply(res -> {
            //由于上一个会出现异常，所以这里不会执行
            return "获取到的数值:" + res;
        }).exceptionally(error -> {
            error.printStackTrace();
            return "出现异常...";
        });

        future.thenAccept(System.out::println);
    }


    /**
     * 计算完毕后的处理2
     * 1.handle(BiFunction<? super T,Throwable,? extends U> fn)
     * 2.handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
     * 3.handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
     * handle方法集和上面的complete方法集没有区别，同样有两个参数一个返回结果和可抛出异常
     * 区别就在于返回值：虽然同样返回CompletableFuture类型，但是里面的参数类型，handle方法是可以自定义的。
     */
    public void handleTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");
            return 666;
        }, executorService);

        //两个CompletableFuture返回值不同
        CompletableFuture<String> handle = cf1.handle((res, error) -> {
            if (null != error) {
                error.printStackTrace();
            }
            System.out.println("res = " + res);
            return "ok";
        });

        try {
            String s = handle.get();
            //结果 ==> ok
            System.out.println(s);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算完成后的操作
     * 1.thenApply(Function<? super T,? extends U> fn)
     * 2.thenApplyAsync(Function<? super T,? extends U> fn)
     * 3.thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * apply方法和handle方法一样，都是结束计算之后的后续操作，
     * 唯一的不同是，handle方法会给出异常，可以让用户自己在内部处理，
     * 而apply方法只有一个返回结果，如果异常了，会被直接抛出，交给上一层处理。
     */
    public void applyTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");
            return "result1";
        }, executorService);

        CompletableFuture<Integer> thenApply = cf1.thenApply((res) -> {
            System.out.println("res = " + res);
            //return res + " after apply";
            return 1;
        });

        try {
            System.out.println(thenApply.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * accept（）三个方法只做最终结果的消费，注意此时返回的CompletableFuture是空返回。
     * 1.thenAccept(Consumer<? super T> action)
     * 2.thenAcceptAsync(Consumer<? super T> action)
     * 3.thenAcceptAsync(Consumer<? super T> action, Executor executor)
     */
    public void acceptTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("task1 do something...");
            return 222;
        }, executorService);

        //只能返回Void
        CompletableFuture<Void> accept = cf1.thenAccept(res -> System.out.println(res + "ok"));
    }

    //endregion

    //region 组合completableFuture

    /**
     * thenApply()上面有使用案例，这里略
     */
    public void applyTest2() {

    }

    public void thenComposeTest() {
        CompletableFuture<List<String>> cf1 = CompletableFuture.supplyAsync(() -> {
            List<String> list1 = new ArrayList<>();
            list1.add("豌豆射手");
            list1.add("向日葵");
            return list1;
        }).thenCompose(list -> {
            return CompletableFuture.supplyAsync(() -> {
                ArrayList<String> list2 = new ArrayList<>();
                list2.add("普通僵尸");
                list2.add("跳跳僵尸");
                // 合并两个list，获取课程所需所有工具
                return Stream
                        .of(list, list2)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
            });
        });
        System.out.println(cf1.join());
    }

    public void thenCombineTest() {
        CompletableFuture<List<String>> cf1 = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<>();
            list.add("土豆雷");
            list.add("玉米投手");
            return list;
        });
        CompletableFuture<List<String>> cf2 = CompletableFuture.supplyAsync(() -> {
            List<String> list = new ArrayList<>();
            list.add("大喷菇");
            list.add("胆小菇");
            return list;
        });

        CompletableFuture<List<String>> cf3 = cf1.thenCombine(cf2, (list1, list2) -> {
            System.out.println("list1 = " + list1);
            System.out.println("list2 = " + list2);
            //合并
            return Stream.of(list1, list2)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        });
        System.out.println(cf3.join());
    }

    //endregion

    //region other test

    /**
     * 使用类实现supplier接口
     */
    public void myTest1() {
        MySupplier supplier = new MySupplier();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier, executorService);
        future.thenAccept(System.out::println);
    }

    public void myTest2() {
        CompletableFuture<Boolean> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("add");
            return true;
        });

        CompletableFuture<Boolean> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("add");
            return true;
        });

        CompletableFuture<Void> cf3 = CompletableFuture.allOf(cf1, cf2);
        CompletableFuture<List<String>> cf4 = cf3.thenApply(v -> {
            Boolean res1 = cf1.join();
            Boolean res2 = cf2.join();
            System.out.println("res1 = > " + res1);
            System.out.println("res2 = > " + res2);
            return Collections.singletonList("111");
        });
        cf4.thenAccept(System.out::println);
    }

    //endregion

    //endregion
}