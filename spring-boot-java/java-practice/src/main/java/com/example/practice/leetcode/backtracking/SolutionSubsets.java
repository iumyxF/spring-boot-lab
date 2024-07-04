package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/4 15:07
 */
public class SolutionSubsets {
    /*
    给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

    示例 1：
    输入：nums = [1,2,3]
    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

    示例 2：
    输入：nums = [0]
    输出：[[],[0]]

    提示：
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
    nums 中的所有元素 互不相同
     */

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // 加入[]
        res.add(new ArrayList<>());
        backtracking(nums, 0);
        return res;
    }

    public void backtracking(int[] nums, int index) {
        //end
        if (temp.size() >= 1) {
            res.add(new ArrayList<>(temp));
            if (index >= nums.length) {
                return;
            }
        }
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            backtracking(nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        SolutionSubsets s = new SolutionSubsets();
        List<List<Integer>> list = s.subsets(arr);
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }
}
