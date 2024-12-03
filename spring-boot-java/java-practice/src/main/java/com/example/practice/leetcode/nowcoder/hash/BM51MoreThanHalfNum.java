package com.example.practice.leetcode.nowcoder.hash;

import java.util.HashMap;

/**
 * @author fzy
 * @description:
 * @date 2024/12/3 17:20
 */
public class BM51MoreThanHalfNum {

    /*
    数组中出现次数超过一半的数字

    给一个长度为的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
    数据范围：n <= 50000,数组中元素的值 0 <= val <= 10000
    要求：空间复杂度：O(1),时间复杂度O(m)
     */

    public int moreThanHalfNumSolution(int[] numbers) {
        HashMap<Integer, Integer> countMap = new HashMap<>(numbers.length);
        int canOutCount = numbers.length % 2 == 0 ? numbers.length / 2 : numbers.length / 2 + 1;
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];
            if (countMap.containsKey(n)) {
                if (countMap.get(n) + 1 >= canOutCount) {
                    return n;
                }
                countMap.put(n, countMap.get(n) + 1);
            } else {
                countMap.put(n, 1);
                if (1 >= canOutCount) {
                    return n;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BM51MoreThanHalfNum s = new BM51MoreThanHalfNum();
        int[] arr = new int[]{1};
        System.out.println(s.moreThanHalfNumSolution(arr));
    }
}
