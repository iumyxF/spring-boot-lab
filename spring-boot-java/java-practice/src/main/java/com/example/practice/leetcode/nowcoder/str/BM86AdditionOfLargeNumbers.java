package com.example.practice.leetcode.nowcoder.str;

/**
 * @author fzy
 * @description:
 * @date 31/12/2024 下午4:20
 */
public class BM86AdditionOfLargeNumbers {

    /*
    以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。

    input "1","99"
    output "100"
     */

    public String solve(String s, String t) {
        StringBuilder res = new StringBuilder();
        int l1 = s.length() - 1;
        int l2 = t.length() - 1;
        boolean add = false;
        while (l1 >= 0 || l2 >= 0 || add) {
            int a = l1 >= 0 ? s.charAt(l1) - '0' : 0;
            int b = l2 >= 0 ? t.charAt(l2) - '0' : 0;
            int sum = add ? a + b + 1 : a + b;
            add = sum >= 10;
            if (add) {
                res.append(sum % 10);
            } else {
                res.append(sum);
            }
            l1--;
            l2--;
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        BM86AdditionOfLargeNumbers s = new BM86AdditionOfLargeNumbers();
        System.out.println(s.solve("999", "111"));
    }
}
