package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/8 15:22
 */
public class SolutionFindSubsequences {

    /*
    给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
    数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。

    示例 1：
    输入：nums = [4,6,7,7]
    输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

    示例 2：
    输入：nums = [4,4,3,2,1]
    输出：[[4,4]]

    提示：
    1 <= nums.length <= 15
    -100 <= nums[i] <= 100

    used[]

     */
    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    public void backtracking(int[] nums, int index) {
        if (temp.size() >= 2) {
            res.add(new ArrayList<>(temp));
        }
        // 记录每层遍历时是否用过元素
        HashSet<Integer> used = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            // 从第二层开始判断
            if (!temp.isEmpty()
                    // 保证有序
                    && temp.get(temp.size() - 1) > nums[i]
                    // 保证不重复添加
                    || used.contains(nums[i])) {
                continue;
            }
            temp.add(nums[i]);
            used.add(nums[i]);
            backtracking(nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * input:[1, 2, 3, 1, 1, 1]
     * exp:[[1,2],[1,2,3],[1,3],[1,1],[1,1,1],[1,1,1,1],[2,3]]
     */
    public static void main(String[] args) {
        //int[] nums = new int[]{4, 6, 7, 7};
        int[] nums = new int[]{1, 2, 3, 1, 1, 1};
        SolutionFindSubsequences s = new SolutionFindSubsequences();
        List<List<Integer>> res = s.findSubsequences(nums);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }
}
