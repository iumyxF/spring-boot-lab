package com.example.juc.test_cas_second;

/**
 * @author iumyxF
 * @description: 使用synchronized 实现简单的cas机制
 * @date 2023/11/22 10:36
 */
public class CasDemo {

    private static Integer num = 0;

    public static void main(String[] args) {
        final CompareAndSwapTest cas = new CompareAndSwapTest();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                long expectedValue = cas.get();
                boolean b = cas.compareAndSwap(expectedValue, (int) (Math.random() * 101));
                if (b) {
                    num++;
                    System.out.println(num);
                }
                //System.out.println(b);
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class CompareAndSwapTest {

    /**
     * 内存地址
     */
    private long value;

    public synchronized long get() {
        return value;
    }

    /**
     * cas:当前值==期望值，则将当前值修改成新值
     */
    public synchronized boolean compareAndSwap(long exceptValue, long newValue) {
        long oldValue = value;
        if (oldValue == exceptValue) {
            this.value = newValue;
            return true;
        } else {
            return false;
        }
    }
}
