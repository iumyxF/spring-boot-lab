package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/11/26 15:39
 */
public class BM44IsValid {

    /*
    给出一个仅包含字符'(' , ')' , '{' , '}' , '[' , ']' 的字符串，判断给出的字符串是否是合法的括号序列
    括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
    数据范围：字符串长度0 <= n <= 10000
    要求：空间复杂度O(),时间复杂度O(m)
     */
    public boolean isValid(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>(3);
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                if (!map.containsKey(c)) {
                    return false;
                }
                stack.push(map.get(c));
            } else {
                if (map.containsKey(c)) {
                    stack.push(map.get(c));
                } else {
                    if (stack.pop() != c) {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        BM44IsValid s = new BM44IsValid();
        System.out.println(s.isValid("}()()()"));
        System.out.println(s.isValid("[]{}["));
        System.out.println(s.isValid("[]{}[]"));
        System.out.println(s.isValid("([{}])"));
    }
}
