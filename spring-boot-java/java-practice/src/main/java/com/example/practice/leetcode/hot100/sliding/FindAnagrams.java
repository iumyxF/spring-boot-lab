package com.example.practice.leetcode.hot100.sliding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/1/21 13:43
 */
public class FindAnagrams {

    /*
    给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
    字母异位词: 是通过重新排列不同单词或短语的字母而形成的单词或短语，并使用所有原字母一次。

    1 <= s.length, p.length <= 3 * 104
    s 和 p 仅包含小写字母

    输入: s = "cbaebabacd", p = "abc"
    输出: [0,6]
    解释:
    起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
    起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。

    输入: s = "abab", p = "ab"
    输出: [0,1,2]
    解释:
    起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
    起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
    起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     */

    /**
     * 超时了
     */
    public List<Integer> findAnagramsTimeOut(String s, String p) {
        int length = s.length();
        if (p.length() > length) {
            return new ArrayList<>();
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();

        for (int left = 0; left < s.length(); left++) {
            char lc = s.charAt(left);
            if (!map.containsKey(lc)) {
                continue;
            }
            HashMap<Character, Integer> tempMap = new HashMap<>(map);
            int right = left;
            for (; right - left < p.length() && right < length; right++) {
                char c = s.charAt(right);
                Integer value = tempMap.get(c);
                if (value == null) {
                    // 有两种情况，1是原本有但是耗完了(跳到上一个元素的后一个元素)，2是根本就没有（跳到right元素的下一个元素）。
                    if (map.containsKey(c)) {
                        break;
                    } else {
                        left = right;
                    }
                    break;
                }
                if (value > 1) {
                    tempMap.put(c, value - 1);
                } else {
                    tempMap.remove(c);
                }
            }
            if (right - left == p.length() && tempMap.isEmpty()) {
                res.add(left);
            }
        }
        return res;
    }


    /**
     * 关键点: 26位的数组来存储词频，然后比较
     * 创建一个大小为 26 的数组 c2 来统计字符串 p 的词频，另外一个同等大小的数组 c1 用来统计「滑动窗口」内的 s 的子串词频。
     * 当两个数组所统计词频相等，说明找到了一个异位组，将窗口的左端点 加入答案。
     */
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        ArrayList<Integer> res = new ArrayList<>();
        // 统计字符串 p 的词频
        for (int i = 0; i < plen; i++) {
            c2[p.charAt(i) - 'a']++;
        }
        for (int left = 0, right = 0; right < slen; right++) {
            // c1 词频+1
            c1[s.charAt(right) - 'a']++;
            if (right - left + 1 > plen) {
                // 超过滑动窗口则减去左边的词频
                c1[s.charAt(left) - 'a']--;
                left++;
            }
            if (check(c1, c2)) {
                res.add(left);
            }
        }
        return res;
    }

    /**
     * 比较两个数组词频是否相同
     */
    public boolean check(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FindAnagrams s = new FindAnagrams();
        System.out.println(s.findAnagrams("cbaebabacd", "abc"));
        System.out.println(s.findAnagrams("abab", "ab"));
        System.out.println(s.findAnagrams("aabaabaabaabaa", "aaa"));
    }
}
