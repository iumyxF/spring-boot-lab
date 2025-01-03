package com.example.practice.leetcode.nowcoder.pointer;

/**
 * @author fzy
 * @description:
 * @date 3/1/2025 上午9:25
 */
public class BM91ReverseStr {

    /*
    写出一个程序，接受一个字符串，然后输出该字符串反转后的字符串。（字符串长度不超过1000）

    输入："abcd"
    输入："dcba"
     */

    public String solve(String str) {
        // write code here
        if (str.length() <= 1) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BM91ReverseStr s = new BM91ReverseStr();
        System.out.println(s.solve("abcd"));
    }
}
