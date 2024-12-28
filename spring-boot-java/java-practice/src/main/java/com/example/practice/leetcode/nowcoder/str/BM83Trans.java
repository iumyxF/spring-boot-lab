package com.example.practice.leetcode.nowcoder.str;

import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/12/28 11:22
 */
public class BM83Trans {

    /*
    对于一个长度为 n 字符串，我们需要对它做一些变形。
    首先这个字符串中包含着一些空格，就像"Hello World"一样，然后我们要做的是把这个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。
    比如"Hello World"变形后就变成了"wORLD hELLO"。

    输入 "This is a sample",16
    输出 "SAMPLE A IS tHIS"

    "nowcoder",8
    "NOWCODER"

    "iOS",3
    "Ios"

     */

    public String trans(String s, int n) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        int slow = 0;
        int fast = 0;
        while (slow < sb.length()) {
            if (fast == sb.length()) {
                String str = sb.substring(slow, fast);
                stack.push(str);
                slow = fast + 1;
                continue;
            }
            char c = sb.charAt(fast);
            if (c == ' ') {
                String str = sb.substring(slow, fast);
                stack.push(str);
                slow = fast + 1;
            } else if (c < 97) {
                sb.setCharAt(fast, (char) (c + 32));
            } else {
                sb.setCharAt(fast, (char) (c - 32));
            }
            fast++;
        }
        StringBuilder res = new StringBuilder();
        if (s.charAt(n - 1) == ' ') {
            res.append(" ");
        }
        while (!stack.isEmpty()) {
            res.append(stack.pop()).append(" ");
        }
        return res.substring(0, res.length() - 1);
    }


    public static void main(String[] args) {
        BM83Trans s = new BM83Trans();
        System.out.println(s.trans("This is a sample", 16));
        System.out.println(s.trans("This is a sample ", 17));
        System.out.println(s.trans("h i ", 4));
        //System.out.println((int) 'a');
        //System.out.println((int) 'A');
        //System.out.println((char) ('a' - 32));
        //System.out.println((char) ('A' + 32));
    }

}
