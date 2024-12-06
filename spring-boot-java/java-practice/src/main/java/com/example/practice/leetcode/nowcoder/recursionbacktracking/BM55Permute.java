package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;

/**
 * @author fzy
 * @description:
 * @date 2024/12/6 15:44
 */
public class BM55Permute {

    /*
    给出一组数字，返回该组数字的所有排列
    例如：
    [1,2,3]的所有排列如下
    [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
    (以数字在数组中的位置靠前为优先级，按字典序排列输出。)

    数据范围：数字个数 0< n <= 6
    要求：空间复杂度O(n!),时间复杂度O(n!)
     */

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[num.length];
        recursion(num, used, path, res);
        return res;
    }

    public void recursion(int[] num, boolean[] used, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res) {
        if (path.size() == num.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (used[i]) {
                continue;
            }
            int value = num[i];
            used[i] = true;
            path.add(value);
            recursion(num, used, path, res);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        BM55Permute s = new BM55Permute();
        System.out.println(s.permute(new int[]{1, 2, 3}));
    }

}
