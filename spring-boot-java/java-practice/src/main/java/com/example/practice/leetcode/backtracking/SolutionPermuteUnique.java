package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/9 14:30
 */
public class SolutionPermuteUnique {

    /*
    给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。

    示例 1：
    输入：nums = [1,1,2]
    输出：
    [[1,1,2],
     [1,2,1],
     [2,1,1]]

    示例 2：
    输入：nums = [1,2,3]
    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    提示：
    1 <= nums.length <= 8
    -10 <= nums[i] <= 10
     */

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();


    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums, new boolean[nums.length]);
        return res;
    }

    private void backtracking(int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            if (!used[i]) {
                temp.add(nums[i]);
                used[i] = true;
                backtracking(nums, used);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * [[0,3,3,3],[3,0,3,3],[3,3,0,3],[3,3,3,0]]
     *
     * @param args
     */
    public static void main(String[] args) {
        //int[] nums = new int[]{1, 1, 2};
        int[] nums = new int[]{3, 3, 0, 3};
        SolutionPermuteUnique s = new SolutionPermuteUnique();
        List<List<Integer>> res = s.permuteUnique(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }
}
