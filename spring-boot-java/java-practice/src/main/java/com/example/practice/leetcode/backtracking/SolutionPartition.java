package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author iumyxF
 * @note 待看
 * @date 2024/7/2 21:01
 */
public class SolutionPartition {

    /*
    给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
    回文串 是正着读和反着读都一样的字符串。

    示例 1：
    输入：s = "google"
    输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]

    示例 2：
    输入：s = "aab"
    输出：[["a","a","b"],["aa","b"]]

    示例 3：
    输入：s = "a"
    输出：[["a"]]
    提示：
    1 <= s.length <= 16
    s 仅由小写英文字母组成
     */
    /*
    回文串：定位 先要找到两个相同的元素

     */

    public String[][] partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), res);
        String[][] resArr = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i).toArray(new String[0]);
        }
        return resArr;
    }

    private void backtracking(String s, int start, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                path.add(s.substring(start, end + 1));
                backtracking(s, end + 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "google";
        SolutionPartition s = new SolutionPartition();
        String[][] strings = s.partition(str);
        for (String[] string : strings) {
            System.out.println(Arrays.toString(string));
        }
    }
}
