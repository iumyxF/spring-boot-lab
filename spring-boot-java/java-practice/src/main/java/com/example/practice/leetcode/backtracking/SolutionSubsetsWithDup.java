package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description: 子集Ⅱ
 * @date 2024/7/5 14:24
 */
public class SolutionSubsetsWithDup {
    /*
    给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。
    解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

    示例 1：
    输入：nums = [1,2,2]
    输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]

    示例 2：
    输入：nums = [0]
    输出：[[],[0]]

    提示：
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
     */

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 加入[]
        res.add(new ArrayList<>());
        Arrays.sort(nums);
        backtracking(nums, 0, new boolean[nums.length]);
        return res;
    }

    public void backtracking(int[] nums, int index, boolean[] used) {
        //end
        if (temp.size() >= 1) {
            res.add(new ArrayList<>(temp));
            if (index >= nums.length) {
                return;
            }
        }
        for (int i = index; i < nums.length; i++) {
            // 判断是否重复统计
            // 1. 当前元素和前一个元素是否相同
            // 2. 前一个元素used = false
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            backtracking(nums, i + 1, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2};
        SolutionSubsetsWithDup s = new SolutionSubsetsWithDup();
        List<List<Integer>> list = s.subsetsWithDup(arr);
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }

}
