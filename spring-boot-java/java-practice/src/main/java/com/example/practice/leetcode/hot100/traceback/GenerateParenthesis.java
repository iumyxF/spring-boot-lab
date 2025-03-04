package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/3/3 13:42
 */
public class GenerateParenthesis {

    /*
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

    输入：n = 3
    输出：["((()))","(()())","(())()","()(())","()()()"]
     */

    public List<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        traceBack(0, 0, n, new StringBuilder(), res);
        return res;
    }

    private void traceBack(int leftNum, int rightNum, int n, StringBuilder path, List<String> res) {
        if (leftNum == n && rightNum == n) {
            res.add(new String(path));
            return;
        }
        if (leftNum < n) {
            path.append("(");
            traceBack(leftNum + 1, rightNum, n, path, res);
            path.deleteCharAt(path.length() - 1);
        }
        if (rightNum < n && leftNum > rightNum) {
            path.append(")");
            traceBack(leftNum, rightNum + 1, n, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis s = new GenerateParenthesis();
        System.out.println(s.generateParenthesis(3));
    }
}
