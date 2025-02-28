package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/28 13:52
 */
public class FullPermutation {

    /*
    给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

    输入：nums = [1,2,3]
    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    public List<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        traceBack(nums, used, new ArrayList<>(), res);
        return res;
    }

    public void traceBack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            traceBack(nums, used, path, res);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
