package com.example.practice.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/1/10 11:18
 */
public class GetPermutation {

    /**
     * 排列序列
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定 n 和 k，返回第 k 个排列。
     */
    public String getPermutation(int n, int k) {
        List<String> all = new ArrayList<>();
        String[] nums = new String[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i + "";
        }
        StringBuilder path = new StringBuilder();
        boolean[] used = new boolean[n];
        dfs(n, k, nums, path, used, all);
        return all.get(k - 1);
    }

    public void dfs(int n, int k, String[] nums, StringBuilder path, boolean[] used, List<String> all) {
        if (path.length() == n) {
            all.add(path.toString());
            return;
        }
        if (all.size() >= k) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.append(nums[i]);
            used[i] = true;
            dfs(n, k, nums, path, used, all);
            path.deleteCharAt(path.length() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        GetPermutation s = new GetPermutation();
        System.out.println(s.getPermutation(3, 3));
    }
}
