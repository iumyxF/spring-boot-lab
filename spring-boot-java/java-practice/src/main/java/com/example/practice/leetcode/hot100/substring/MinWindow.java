package com.example.practice.leetcode.hot100.substring;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author fzy
 * @description:
 * @date 2025/1/23 9:25
 */
public class MinWindow {

    /*
    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    注意：
    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
    如果 s 中存在这样的子串，我们保证它是唯一的答案。

    m == s.length
    n == t.length
    1 <= m, n <= 105
    s 和 t 由英文字母组成

    输入：s = "ADOBECODEBANC", t = "ABC"， "BANCADOBECODE"
    输出："BANC"
    解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。

    输入：s = "a", t = "a"
    输出："a"
    解释：整个字符串 s 是最小覆盖子串。

    输入: s = "a", t = "aa"
    输出: ""
    解释: t 中两个字符 'a' 均应包含在 s 的子串中，
    因此没有符合条件的子字符串，返回空字符串。


    52位字母的数组 检查字串是否相同
     */

    /**
     * 超时了
     * 思路：判断动态数组和目标数组是否相同，用56个数组保存字母出现次数来判断
     */
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return "";
        }
        // 初始化两个数组
        int[] sArr = new int[58];
        int[] tArr = new int[58];
        for (int i = 0; i < tLen; i++) {
            tArr[t.charAt(i) - 'A']++;
        }

        int start = 0;
        int end = 0;

        int minLen = Integer.MAX_VALUE;
        // 先向右扩展，读取到字串后，进行收缩，
        for (int left = 0; left < sLen; left++) {
            // 剪枝
            if (tArr[s.charAt(left) - 'A'] == 0) {
                continue;
            }
            for (int right = left; right < sLen; right++) {
                sArr[s.charAt(right) - 'A']++;
                if (check(sArr, tArr)) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        start = left;
                        end = right + 1;
                        break;
                    }
                }
            }
            Arrays.fill(sArr, 0);
        }
        return s.substring(start, end);
    }

    public boolean check(int[] source, int[] target) {
        for (int i = 0; i < 58; i++) {
            // 可以多 但是不能少
            if (source[i] < target[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用 hashmap 优化比较过程
     * 注意左移的时候要判断数量再控制size--
     */
    public String minWindow2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return "";
        }
        // 滑动窗口
        HashMap<Character, Integer> window = new HashMap<>();
        // 所需元素
        HashMap<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;

        int start = 0;
        int minLen = Integer.MAX_VALUE;

        // 记录是否元素都满足了,这里size指的是满足need的元素个数，不是window.size()
        int windowSize = 0;

        while (right < sLen) {
            char c = s.charAt(right);
            // 右移
            if (need.containsKey(c)) {
                int count = window.getOrDefault(c, 0) + 1;
                window.put(c, count);
                if (count == need.get(c)) {
                    windowSize++;
                }
            }
            // 左移
            while (windowSize == need.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                } else {
                    char lc = s.charAt(left);
                    if (need.containsKey(lc)) {
                        Integer count = window.get(lc);
                        if (count - 1 < need.get(lc)) {
                            windowSize--;
                        }
                        window.put(lc, count - 1);
                    }
                    left++;
                }
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        MinWindow s = new MinWindow();
        System.out.println(s.minWindow2("ADOBECODEBANC", "ABC"));
        //System.out.println(s.minWindow2("ab", "a"));
        //System.out.println(s.minWindow2("abc", "cba"));
    }
}