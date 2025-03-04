package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/3/4 10:13
 */
public class Partition {

    /*
    给你一个字符串 s，请你将 s 分割成一些 子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

    输入：s = "aab"
    输出：[["a","a","b"],["aa","b"]]

     */

    public List<List<String>> partition(String s) {
        // 缓存已判断过的回文子串结果
        Boolean[][] memo = new Boolean[s.length()][s.length()];
        ArrayList<List<String>> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res, memo);
        return res;
    }

    private void backtrack(String s, int start, List<String> path, List<List<String>> res, Boolean[][] memo) {
        // 结尾
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end, memo)) {
                path.add(s.substring(start, end + 1));
                backtrack(s, end + 1, path, res, memo);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right, Boolean[][] memo) {
        if (memo[left][right] != null) {
            return memo[left][right];
        }
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                memo[left - 1][right + 1] = false;
                return false;
            }
        }
        memo[left][right] = true;
        return true;
    }

    public static void main(String[] args) {
        Partition s = new Partition();
        System.out.println(s.partition("aab"));
    }
}
