package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/9 14:16
 */
public class SolutionPermute {

    /*
    给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。

    示例 1：
    输入：nums = [1,2,3]
    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    示例 2：
    输入：nums = [0,1]
    输出：[[0,1],[1,0]]

    示例 3：
    输入：nums = [1]
    输出：[[1]]
    提示：

    1 <= nums.length <= 6
    -10 <= nums[i] <= 10
    nums 中的所有整数 互不相同
     */

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums, new boolean[nums.length]);
        return res;
    }

    private void backtracking(int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                temp.add(nums[i]);
                used[i] = true;
                backtracking(nums, used);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{1, 2, 3};
        //int[] nums = new int[]{0,1 };
        int[] nums = new int[]{1};
        SolutionPermute s = new SolutionPermute();
        List<List<Integer>> res = s.permute(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }
}
