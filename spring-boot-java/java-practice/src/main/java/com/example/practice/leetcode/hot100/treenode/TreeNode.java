package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/18 9:23
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
