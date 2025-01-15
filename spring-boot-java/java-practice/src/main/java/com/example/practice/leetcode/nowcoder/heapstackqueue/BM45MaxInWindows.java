package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.ArrayList;

/**
 * @author fzy
 * @description:
 * @date 2025/1/15 15:15
 */
public class BM45MaxInWindows {


    /*
    给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值。

    例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
    针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
    {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
    {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     */

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num.length < size) {
            return res;
        }
        // 初始化
        int left = 0;
        int right = 0;
        int len = num.length;

        int tempMaxIndex = 0;
        int tempMaxValue = Integer.MIN_VALUE;
        while (right < size) {
            int value = num[right];
            if (value >= tempMaxValue) {
                tempMaxValue = value;
                tempMaxIndex = right;
            }
            right++;
        }
        res.add(tempMaxValue);

        while (right < len) {
            int rv = num[right];
            if (rv == tempMaxValue) {
                tempMaxIndex = right;
            } else if (rv > tempMaxValue) {
                tempMaxIndex = right;
                tempMaxValue = rv;
            } else {
                if (left == tempMaxIndex) {
                    // 重新获取最大值
                    tempMaxIndex = getMaxIndex(num, left + 1, right);
                    tempMaxValue = num[tempMaxIndex];
                }
            }
            res.add(tempMaxValue);
            right++;
            left++;
        }

        return res;
    }

    private int getMaxIndex(int[] arr, int left, int right) {
        int res = left;
        int value = arr[left];
        left++;
        for (int i = left; i < right; i++) {
            if (arr[i] > value) {
                res = i;
                value = arr[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BM45MaxInWindows s = new BM45MaxInWindows();
        System.out.println(s.maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3));
        System.out.println(s.maxInWindows(new int[]{0,14,12,11}, 4));
    }
}
