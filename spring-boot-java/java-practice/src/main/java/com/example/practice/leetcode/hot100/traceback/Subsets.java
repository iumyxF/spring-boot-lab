package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/28 14:02
 */
public class Subsets {

    /*
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

    解集 不能 包含重复的子集(数组的 子集 是从数组中选择一些元素（可能为空）)。你可以按 任意顺序 返回解集。

    输入：nums = [1,2,3]
    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     */

    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        traceBack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void traceBack(int[] nums, int index, List<Integer> path, List<List<Integer>> res) {
        if (path.size() <= nums.length) {
            res.add(new ArrayList<>(path));
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            traceBack(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets s = new Subsets();
        List<List<Integer>> lists = s.subsets(new int[]{1, 2, 3});
        lists.forEach(System.out::println);
    }
}
