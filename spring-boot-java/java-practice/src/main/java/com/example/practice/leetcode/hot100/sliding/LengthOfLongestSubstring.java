package com.example.practice.leetcode.hot100.sliding;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @description:
 * @date 2025/1/21 9:50
 */
public class LengthOfLongestSubstring {

    /*
    给定一个字符串 s，请你找出其中不含有重复字符的最长子串的长度。

    输入: s = "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

     */

    /**
     * 时间复杂度 O(N^2)
     * 因为有个indexOf运算
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String c = String.valueOf(s.charAt(i));
            int index = sb.indexOf(c);
            if (-1 == index) {
                sb.append(c);
            } else {
                max = Math.max(sb.length(), max);
                sb = new StringBuilder(sb.substring(index + 1, sb.length()));
                sb.append(c);
            }
        }
        return Math.max(sb.length(), max);
    }

    /**
     * 优化空间复杂度
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int max = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int start = 0;
        for (int end = 0; end < n; end++) {
            char c = s.charAt(end);
            // 如果字符已经在 map 中，更新 start 位置
            if (charMap.containsKey(c)) {
                start = Math.max(start, charMap.get(c) + 1);
            }
            // 更新字符最后出现的位置
            charMap.put(c, end);
            // 计算当前窗口的长度，并更新 max
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring s = new LengthOfLongestSubstring();
        //System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
        //System.out.println(s.lengthOfLongestSubstring("a    bcabcb   b"));
        //System.out.println(s.lengthOfLongestSubstring("    abcabcbb"));
        //System.out.println(s.lengthOfLongestSubstring(" ")); // exp = 1

        System.out.println(s.lengthOfLongestSubstring2("abccbcbb"));
    }
}
