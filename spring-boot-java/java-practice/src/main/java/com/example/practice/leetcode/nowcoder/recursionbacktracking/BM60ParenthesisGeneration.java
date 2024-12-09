package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feng
 * @description:
 * @date 2024/12/6 21:07
 */
public class BM60ParenthesisGeneration {

    /*
    给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
    例如，给出n=3，解集为：
    "((()))", "(()())", "(())()", "()()()", "()(())"
     */

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        dfs(new StringBuilder(), n, n, res);
        return res;
    }

    public void dfs(StringBuilder path, int leftCount, int rightCount, List<String> res) {
        if (leftCount == 0 && rightCount == 0) {
            res.add(path.toString());
            return;
        }
        if (leftCount > 0) {
            path.append("(");
            dfs(path, leftCount - 1, rightCount, res);
            path.deleteCharAt(path.length() - 1);
        }
        // path里面的左括号要比右括号多，所以leftCount要小于rightCount
        if (leftCount < rightCount && rightCount > 0) {
            path.append(")");
            dfs(path, leftCount, rightCount - 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        BM60ParenthesisGeneration s = new BM60ParenthesisGeneration();
        System.out.println(s.generateParenthesis(3));
    }
}
