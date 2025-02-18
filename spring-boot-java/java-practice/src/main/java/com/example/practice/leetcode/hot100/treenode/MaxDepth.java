package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/18 9:26
 */
public class MaxDepth {

    /*
    给定一个二叉树 root ，返回其最大深度。
    二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     */

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ll = maxDepth(root.left);
        int rl = maxDepth(root.right);
        return Math.max(ll, rl) + 1;
    }
}
