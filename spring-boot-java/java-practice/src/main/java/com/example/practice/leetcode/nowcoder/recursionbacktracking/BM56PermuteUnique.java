package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author fzy
 * @description:
 * @date 2024/12/6 15:53
 */
public class BM56PermuteUnique {

    /*
    给出一组可能包含重复项的数字，返回该组数字的所有排列。结果以字典序升序排列。

    输入：[1,1,2]
    输出：[[1,1,2],[1,2,1],[2,1,1]]
     */

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        Arrays.sort(num);
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
            if (i > 0 && num[i] == num[i - 1] && !used[i - 1]) {
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
        BM56PermuteUnique s = new BM56PermuteUnique();
        System.out.println(s.permuteUnique(new int[]{1, 1, 2}));
    }
}
