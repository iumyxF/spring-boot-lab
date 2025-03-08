package com.example.practice.leetcode.hot100.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2025/3/7 16:53
 */
public class IsValidParenthesis {

    /*
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

    有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    每个右括号都有一个对应的相同类型的左括号。

    输入：s = "()"
    输出：true

    输入：s = "()[]{}"
    输出：true

    输入：s = "(]"
    输出：false

    输入：s = "([])"
    输出：true
     */

    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>(3);
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        int length = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        IsValidParenthesis s = new IsValidParenthesis();
        System.out.println(s.isValid("()"));
        System.out.println(s.isValid("(){}[]"));
        System.out.println(s.isValid("((()))"));
        System.out.println(s.isValid("(()"));
    }
}
