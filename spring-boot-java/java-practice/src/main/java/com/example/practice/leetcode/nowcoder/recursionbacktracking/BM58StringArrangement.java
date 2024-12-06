package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author feng
 * @description:
 * @date 2024/12/6 20:51
 */
public class BM58StringArrangement {

    /*
    输入一个长度为 n 字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组。
    例如输入字符串ABC,则输出由字符A,B,C所能排列出来的所有字符串ABC,ACB,BAC,BCA,CBA和CAB。
     */

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        char[] array = str.toCharArray();
        Arrays.sort(array);
        boolean[] used = new boolean[str.length()];
        dfs(array, used, new StringBuilder(), res);
        return res;
    }

    public void dfs(char[] arr, boolean[] used, StringBuilder path, ArrayList<String> res) {
        if (path.length() == arr.length) {
            res.add(new String(path));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && arr[i - 1] == arr[i] && !used[i - 1]) {
                continue;
            }
            path.append(arr[i]);
            used[i] = true;
            dfs(arr, used, path, res);
            used[i] = false;
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        BM58StringArrangement s = new BM58StringArrangement();
        System.out.println(s.Permutation("ABC"));
        System.out.println(s.Permutation(""));
        System.out.println(s.Permutation("aab"));
    }
}
