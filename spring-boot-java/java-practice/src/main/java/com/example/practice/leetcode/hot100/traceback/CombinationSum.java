package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/3/3 10:19
 */
public class CombinationSum {

    /*
    39. 组合总和
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
    找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
    并以列表形式返回。你可以按 任意顺序 返回这些组合。

    candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

    对于给定的输入，保证和为 target 的不同组合数少于 150 个。

    输入：candidates = [2,3,6,7], target = 7
    输出：[[2,2,3],[7]]
    解释：2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。7 也是一个候选， 7 = 7 。仅有这两种组合。
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        traceBack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void traceBack(int[] candidates, int target, int index, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int value = candidates[i];
            if (value > target) {
                break;
            }
            path.add(value);
            traceBack(candidates, target - value, i, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum s = new CombinationSum();
        List<List<Integer>> list = s.combinationSum(new int[]{2, 3, 6, 7}, 7);
        list.forEach(System.out::println);
    }
}
