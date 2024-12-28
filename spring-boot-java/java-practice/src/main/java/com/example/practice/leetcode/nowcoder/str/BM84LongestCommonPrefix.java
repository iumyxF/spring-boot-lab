package com.example.practice.leetcode.nowcoder.str;

import java.util.ArrayList;

/**
 * @author fzy
 * @description:
 * @date 2024/12/28 13:54
 */
public class BM84LongestCommonPrefix {

    /*
    给你一个大小为 n 的字符串数组 strs ，其中包含n个字符串 ,
    编写一个函数来查找字符串数组中的最长公共前缀，返回这个公共前缀。

    输入 ["abca","abc","abca","abc","abcc"]
    输出 "abc"

    前缀的定义：
    对于一个字符串S，它的前缀是指从字符串S的第一个字符开始，到字符串S的某个位置结束的子字符串。简单来说，就是字符串开头的一部分。
    例如，对于字符串"abcde"，它的前缀有""（空字符串）、"a"、"ab"、"abc"、"abcd"、"abcde"
    后缀的定义：
    对于一个字符串S，它的后缀是指从字符串S中的某个位置开始，到字符串的最后一个字符结束的子字符串。
    例如，对于字符串"abcde"，它的后缀有""（空字符串）、"e"、"de"、"cde"、"bcde"、"abcde"。
     */

    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int index = 0;
        while (index != -1) {
            boolean allEq = true;
            char cur = 0;
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].length() <= index) {
                    index = -1;
                    break;
                }
                if (i == 0) {
                    cur = strs[i].charAt(index);
                } else {
                    if (cur != strs[i].charAt(index)) {
                        allEq = false;
                        index = -1;
                        break;
                    }
                }
            }
            if (allEq && index != -1) {
                res.append(cur);
                index++;
            }
        }
        return res.toString();
    }

    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 纵向遍历
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            // 从第二个字符串开始遍历 所以 j=1
            for (int j = 1; j < strs.length; j++) {
                // 判断长度和判断当前字符
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        BM84LongestCommonPrefix s = new BM84LongestCommonPrefix();

        ArrayList<String> list = new ArrayList<>();
        //"abca","abc","abca","abc","abcc"
        list.add("abca");
        list.add("abc");
        list.add("abca");
        list.add("abc");
        list.add("abcc");

        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i);
        }
        System.out.println(s.longestCommonPrefix2(strArr));
    }
}
