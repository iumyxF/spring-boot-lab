package com.example.files.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/6/27 16:22
 */
class Solution {

    //public static List<List<Integer>> res = new ArrayList<>();
    //
    //public static List<Integer> temp = new ArrayList<>();
    //
    //public List<List<Integer>> combine(int n, int k) {
    //    backtracking(n, k, 1);
    //    return res;
    //}
    //
    ///**
    // * @param n
    // * @param k     数量
    // * @param index
    // */
    //public void backtracking(int n, int k, int index) {
    //    if (temp.size() == k) {
    //        res.add(new ArrayList<>(temp));
    //        return;
    //    }
    //    for (int i = index; i <= n - (k - temp.size()) + 1; i++) {
    //        temp.add(i);
    //        backtracking(n, k, i + 1);
    //        temp.remove(temp.size() - 1);
    //    }
    //}

    ArrayList<List<Integer>> res = new ArrayList<>();

    ArrayList<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        // k =3 n = 7  ==> 3个元素相加等于7
        backtracking(k, n, 1, 0);
        return res;
    }

    public void backtracking(int k, int n, int index, int sum) {
        // 终止条件
        if (temp.size() == k) {
            if (sum == n) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = index; i <= 9 - (n - sum) + 1; i++) {
            sum += i;
            temp.add(i);

            backtracking(k, n, i + 1, sum);

            sum -= temp.get(temp.size() - 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = new Solution().combinationSum3(3, 7);
        System.out.println(combine);
    }
}
