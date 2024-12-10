package com.example.practice.leetcode.nowcoder.dynamicprogramming;

/**
 * @author fzy
 * @description:
 * @date 2024/12/10 8:55
 */
public class BM62Fibonacci {

    /*
    大家都知道波那契数列，现在要求输入一个正整数，请你输出斐波那契数列的第n项。
    斐波那契数列是一个满足
    fb(x)=         1             x = 1,2
           fib(x-1)+fib(x-2)     x > 2
    数列数据范围：1 <= n <= 40
    要求：空间复杂度O(1),时间复杂度O(n),本题也有时间复杂度O(logN)的解法
     */

    public int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public int fibonacci2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int j = 0;
        int k = 1;
        int l = j + k;
        while (n - 2 > 0) {
            j = k;
            k = l;
            l = j + k;
            n--;
        }
        return l;
    }

    public static void main(String[] args) {
        BM62Fibonacci s = new BM62Fibonacci();
        System.out.println(s.fibonacci(22));
        //System.out.println(s.fibonacci2(4));
        System.out.println(s.fibonacci2(22));
    }
}
