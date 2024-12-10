package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/10 9:06
 */
public class BM63JumpFloor {

    /*
    一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）

    解析 跳到当前台阶的方式，如果可能是前一级或者前两级
    fb(x)=         1             x = 1
                   2             x = 2
           fib(x-1)+fib(x-2)     x > 2
     */

    public int jumpFloor(int number) {
        if (number <= 2) {
            return number;
        }
        int q = 0;
        int p = 1;
        int k = 2;
        while (number - 2 > 0) {
            q = p;
            p = k;
            k = q + p;
            number--;
        }
        return k;
    }

    public static void main(String[] args) {
        BM63JumpFloor s = new BM63JumpFloor();
        System.out.println(s.jumpFloor(7));
    }
}
