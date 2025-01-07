package com.example.practice.leetcode.nowcoder.simulation;

import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 7/1/2025 上午11:35
 */
public class BM97RotatingArray {


    /*
    一个数组A中存有 n 个整数，在不允许使用另外数组的前提下，将每个整数循环向右移 M（ M >=0）个位置，
    即将A中的数据由（A0 A1 ... AN-1 ）变换为（AN-M ... AN-1 A0 A1 ... AN-M-1 ）（最后 M 个数循环移至最前面的 M 个位置）。
    如果需要考虑程序移动数据的次数尽量少，要如何设计移动的方法？

    6,2,[1,2,3,4,5,6]
    [5,6,1,2,3,4]

    [5,6,3,4,]
     */
    public int[] solve(int n, int m, int[] a) {
        // 防止 m > n
        m = m % n;
        // 整段反转
        reversal(a, 0, n - 1);
        // 前半段反转
        reversal(a, 0, m - 1);
        // 后半段反转
        reversal(a, m, n - 1);
        return a;
    }

    public void reversal(int[] a, int left, int right) {
        while (left < right) {
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        BM97RotatingArray s = new BM97RotatingArray();
        //int[] solve = s.solve(6, 2, new int[]{1, 2, 3, 4, 5, 6});
        //int[] solve = s.solve(7, 2, new int[]{1, 2, 3, 4, 5, 6, 7});
        //int[] solve = s.solve(6, 4, new int[]{1, 2, 3, 4, 5, 6});
        //6,7,[1,2,3,4,5,6]
        int[] solve = s.solve(6, 7, new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(Arrays.toString(solve));
    }
}
