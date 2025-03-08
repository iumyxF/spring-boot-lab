package com.example.practice.leetcode.hot100.stack;

import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/3/7 17:09
 */
public class DecodeString {

    /*
    给定一个经过编码的字符串，返回它解码后的字符串。
    编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
    你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
    此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

    输入：s = "3[a]2[bc]"
    输出："aaabcbc"

    输入：s = "3[a2[c]]"
    输出："accaccacc"

    输入：s = "2[abc]3[cd]ef"
    输出："abcabccdcdcdef"

    输入：s = "abc3[cd]xyz"
    输出："abccdcdcdxyz"
     */

    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        int len = s.length();
        int left = 0;
        while (left < len) {
            int right = left + 1;
            if (isNumber(s.charAt(left))) {
                while (isNumber(s.charAt(right))) {
                    right++;
                }
            }
            String str = s.substring(left, right);
            if ("]".equals(str)) {
                StringBuilder temp = new StringBuilder();
                String pop = stack.pop();
                while (!"[".equals(pop)) {
                    temp.insert(0, pop);
                    pop = stack.pop();
                }
                int count = Integer.parseInt(stack.pop());
                stack.push(copyString(count, temp).toString());
            } else {
                stack.push(str);
            }
            left = right;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public StringBuilder copyString(int copyCount, StringBuilder sb) {
        String s = sb.toString();
        for (int i = 0; i < copyCount - 1; i++) {
            sb.append(s);
        }
        return sb;
    }

    public static void main(String[] args) {
        DecodeString s = new DecodeString();
        //System.out.println(s.decodeString("3[a]2[bc]"));
        //System.out.println(s.decodeString("3[a2[c]]"));
        System.out.println(s.decodeString("10[leetcode]"));
    }

}
