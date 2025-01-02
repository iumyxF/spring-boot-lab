package com.example.practice.leetcode.nowcoder.pointer;

/**
 * @author fzy
 * @description:
 * @date 2/1/2025 上午9:49
 */
public class BM88Judge {

    /*
    给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
     */

    public boolean judge(String str) {
        if (str.length() == 1) {
            return true;
        }
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BM88Judge s = new BM88Judge();
        System.out.println(s.judge("absba"));
        System.out.println(s.judge("ranko"));
        System.out.println(s.judge("yamatomaya"));
        System.out.println(s.judge("a"));
    }
}
