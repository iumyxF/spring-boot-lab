package com.example.juc.test_volatile_first;

/**
 * @author iumyxF
 * @description: volatile关键字
 * 1.volatile使用场景 双重校验锁实现对象单例
 * @date 2023/11/22 9:26
 */
public class VolatileDemo {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();
        while (true) {
            //如果不加volatile 在主线程的while循环中是不知道flag变化了，所以会一直循环等待
            if (td.isFlag()) {
                System.out.println("-----------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {
    private volatile boolean flag = false;

    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }
}