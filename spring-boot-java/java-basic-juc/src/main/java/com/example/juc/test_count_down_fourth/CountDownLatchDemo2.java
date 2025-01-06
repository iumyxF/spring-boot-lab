package com.example.juc.test_count_down_fourth;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author fzy
 * @description:
 * @date 2024/5/18 11:34
 */
public class CountDownLatchDemo2 {


    private static final Map<String, CountDownLatch> COUNT_DOWN_LATCH_MAP = new HashMap<>();

    private static final Random RANDOM = new Random();

    public void endVoting(String votingKey) {

        CountDownLatch countDownLatch = COUNT_DOWN_LATCH_MAP.get(votingKey);
        try {
            for (int i = 1; i <= 5; i++) {
                int num = i;
                Thread thread = new Thread(() -> counting(votingKey, num), "线程" + i);
                thread.start();
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("结束表决...");
    }

    public void counting(String voteKey, Integer num) {
        //通过voteKey获取votingKey
        CountDownLatch countDownLatch = COUNT_DOWN_LATCH_MAP.get("test");
        System.out.println("统计表决" + num + "结果中...");
        try {
            Thread.sleep(RANDOM.nextInt(3) * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("统计表决" + num + "结束");
        countDownLatch.countDown();
    }


    public static void main(String[] args) {
        COUNT_DOWN_LATCH_MAP.put("test", new CountDownLatch(5));
        new CountDownLatchDemo2().endVoting("test");
    }
}
