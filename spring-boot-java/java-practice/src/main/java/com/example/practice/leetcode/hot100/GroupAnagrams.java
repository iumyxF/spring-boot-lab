package com.example.practice.leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/1/17 10:05
 */
public class GroupAnagrams {

    /*
    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

    输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    输出: [["bat"],["nat","tan"],["ate","eat","tea"]]

    输入: strs = [""]
    输出: [[""]]

    输入: strs = ["a"]
    输出: [["a"]]

    1 <= strs.length <= 104
    0 <= strs[i].length <= 100
    strs[i] 仅包含小写字母

    1. 如何判断str1和str2是不是 异位词？ "apple"和"aplle"区别？

    把每个字符的所有排列组合保存到一个map，key = 变形字符，value = 未变形的字符 （内存占用问题）

     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        // key = 排序后的String，value = str中的字符
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = Arrays.toString(chars);
            if (i == 0) {
                ArrayList<String> values = new ArrayList<>();
                values.add(str);
                map.put(sortedStr, values);
            } else {
                if (map.containsKey(sortedStr)) {
                    List<String> values = map.get(sortedStr);
                    values.add(str);
                } else {
                    ArrayList<String> values = new ArrayList<>();
                    values.add(str);
                    map.put(sortedStr, values);
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    public boolean isEctopicWords(String s1, String s2) {
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        System.out.println(Arrays.toString(array1));
        return Arrays.equals(array1, array2);
    }

    public static void main(String[] args) {
        GroupAnagrams s = new GroupAnagrams();
        //System.out.println(s.isEctopicWords("eat", "tae"));
        System.out.println(s.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
