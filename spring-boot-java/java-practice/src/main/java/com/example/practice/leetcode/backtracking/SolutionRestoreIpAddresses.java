package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @note 待看
 * @description: 复原IP地址
 * @date 2024/7/3 16:00
 */
public class SolutionRestoreIpAddresses {
    /*
    给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
    有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。

    示例 1：
    输入：s = "25525511135"
    输出：["255.255.11.135","255.255.111.35"]

    示例 2：
    输入：s = "0000"
    输出：["0.0.0.0"]

    示例 3：
    输入：s = "1111"
    输出：["1.1.1.1"]

    示例 4：
    输入：s = "010010"
    输出：["0.10.0.10","0.100.1.0"]

    示例 5：
    输入：s = "10203040"
    输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
     */

    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        StringBuilder sb = new StringBuilder(s);
        backTracking(sb, 0, 0);
        return result;
    }

    /**
     * @param s          字符串
     * @param startIndex 分割下标
     * @param dotCount   逗号数量
     */
    private void backTracking(StringBuilder s, int startIndex, int dotCount) {
        if (dotCount == 3) {
            //验证最后一段是否合法
            if (isValid(s, startIndex, s.length() - 1)) {
                result.add(s.toString());
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s.insert(i + 1, '.');
                backTracking(s, i + 2, dotCount + 1);
                s.deleteCharAt(i + 1);
            } else {
                break;
            }
        }
    }

    private boolean isValid(StringBuilder s, int start, int end) {
        if (start > end) {
            return false;
        }
        // 排除开头 0.x的地址
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
