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
    List<List<String>> res = new ArrayList<>();

    List<String> temp = new ArrayList<>();

    public String[][] partition(String s) {
        backtracking(s, 0);
        String[][] resArr = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i).toArray(new String[0]);
        }
        return resArr;
    }


    //public void backtracking(String str, int index) {
    //    // 结束条件
    //    if (index == str.length()) {
    //        res.add(new ArrayList<>(temp));
    //        return;
    //    }
    //    for (int i = index; i < str.length(); i++) {
    //        if (isPalindrome(str, index, i)) {
    //            String s = str.substring(index, i + 1);
    //            temp.add(s);
    //        } else {
    //            continue;
    //        }
    //        backtracking(str, i + 1);
    //        temp.remove(temp.size() - 1);
    //    }
    //}

    public void backtracking(String s, int splitIndex) {
        // 分割到最后一个字符串 结束
        if (splitIndex == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = splitIndex; i < s.length(); i++) {
            if (isPalindrome(s, splitIndex, i)) {
                String substring = s.substring(splitIndex, i);
                temp.add(substring);
            } else {
                continue;
            }
            backtracking(s, i + 1);
            temp.remove(temp.size() - 1);
        }

    }

    /**
     * 判断是否是回文串
     */
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
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
