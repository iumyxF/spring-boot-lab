package com.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author iumyxF
 * @description: 多线程循环打印ABC
 * @date 2024/7/1 11:32
 */
public class practice001 {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new PrinterThead("A"));
        executor.execute(new PrinterThead("B"));
        executor.execute(new PrinterThead("C"));
        executor.shutdown();
    }

}

class PrinterThead implements Runnable {

    private final String name;

    private static final String[] arr = new String[]{"A", "B", "C"};

    static AtomicInteger index = new AtomicInteger(0);

    private static final Integer MAX_COUNT = 6;

    public PrinterThead(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index.get() < MAX_COUNT) {
            if (this.name.equals(arr[index.get() % 3])) {
                printAndPlus(this.name);
            }
        }
    }

    private void printAndPlus(String str) {
        System.out.println(Thread.currentThread().getName() + ":" + str);
        index.incrementAndGet();
    }
}