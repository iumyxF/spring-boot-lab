package com.example.utils;

import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @Date 2023/2/27 14:02
 * @author iumyxF
 */
public class SemaphoreUtils {
    /**
     * SemaphoreUtils 日志控制器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreUtils.class);

    /**
     * 获取信号量
     *
     * @param semaphore
     * @return 获取是否成功
     */
    public static boolean tryAcquire(Semaphore semaphore) {
        boolean flag = false;
        try {
            flag = semaphore.tryAcquire();
        } catch (Exception e) {
            LOGGER.error("获取信号量异常", e);
        }
        return flag;
    }

    /**
     * 释放信号量
     *
     * @param semaphore
     */
    public static void release(Semaphore semaphore) {
        try {
            semaphore.release();
        } catch (Exception e) {
            LOGGER.error("释放信号量异常", e);
        }
    }
}
