package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/6/29 11:29
 */
public class SolutionCombinationSum {

    /*
    给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，
    找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
    candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
    对于给定的输入，保证和为 target 的唯一组合数少于 150 个。

    示例 1：
    输入: candidates = [2,3,6,7], target = 7
    输出: [[7],[2,2,3]]

    示例 2：
    输入: candidates = [2,3,5], target = 8
    输出: [[2,2,2,2],[2,3,3],[3,5]]

    示例 3：
    输入: candidates = [2], target = 1
    输出: []

    示例 4：
    输入: candidates = [1], target = 1
    输出: [[1]]

    示例 5：
    输入: candidates = [1], target = 2
    输出: [[1,1]]

    提示：
    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    candidate 中的每个元素都是独一无二的。
    1 <= target <= 500
     */

    ArrayList<List<Integer>> res = new ArrayList<>();

    ArrayList<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //int[] sorted = Arrays.stream(candidates).sorted().toArray();
        backtracking(candidates, target, 0, 0);
        return res;
    }


    public void backtracking(int[] candidates, int target, int index, int sum) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int n = candidates[i];
            sum += n;
            temp.add(n);
            backtracking(candidates, target, index, sum);
            index++;
            sum -= n;
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 5};
        int target = 8;
        SolutionCombinationSum s = new SolutionCombinationSum();
        List<List<Integer>> res = s.combinationSum(candidates, target);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

}
