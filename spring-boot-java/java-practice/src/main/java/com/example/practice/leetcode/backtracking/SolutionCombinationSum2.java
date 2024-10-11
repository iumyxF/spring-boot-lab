package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/7/1 14:06
 */
public class SolutionCombinationSum2 {

    /*
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    candidates 中的每个数字在每个组合中只能使用 一次 。
    注意：解集不能包含重复的组合。

    示例 1:
    输入: candidates = [10,1,2,7,6,1,5], target = 8,
    输出:
    [
    [1,1,6],
    [1,2,5],
    [1,7],
    [2,6]
    ]

    示例 2:
    输入: candidates = [2,5,2,1,2], target = 5,
    输出:
    [
    [1,2,2],
    [5]
    ]
    提示:
    1 <= candidates.length <= 100
    1 <= candidates[i] <= 50
    1 <= target <= 30
     */

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();


    /**
     * 特点：使用了used数组对使用过的元素进行标记处理
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] sorted = Arrays.stream(candidates).sorted().toArray();
        backtracking(sorted, target, 0, 0, new boolean[candidates.length]);
        return res;
    }

    public void backtracking(int[] candidates, int target, int sum, int index, boolean[] used) {
        if (sum > target) {
            return;
        }
        if (target == sum) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            // 重点
            // candidates[i] == candidates[i - 1] 相邻两个元素相同
            // 而且 前一个元素没被使用，说明当前这个元素已经有保存过记录 可以忽略
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            sum += candidates[i];
            temp.add(candidates[i]);
            used[i] = true;
            backtracking(candidates, target, sum, i + 1, used);
            sum -= candidates[i];
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        //int[] candidates = {2,6};
        int target = 8;
        SolutionCombinationSum2 s = new SolutionCombinationSum2();
        List<List<Integer>> res = s.combinationSum2(candidates, target);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }
}
