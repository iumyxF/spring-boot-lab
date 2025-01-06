package com.example.design.pattern.adapter_pattern.test02;

/**
 * @author iumyxF
 * @description: 抽象成绩操作类：目标接口
 * @date 2024/1/5 11:21
 */
public interface ScoreOperation {

    /**
     * 成绩排序
     */
    int[] sort(int[] array);

    /**
     * 成绩查找
     */
    int search(int[] array, int key);
}
