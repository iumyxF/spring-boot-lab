package com.example.practice.leetcode.nowcoder.recursionbacktracking;

import java.util.ArrayList;

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
        return res;
    }

    public void dfs(StringBuilder path, int leftCount, int rightCount, ArrayList<String> res) {
    }

    public static void main(String[] args) {
        BM60ParenthesisGeneration s = new BM60ParenthesisGeneration();
        System.out.println(s.generateParenthesis(3));
    }
}
