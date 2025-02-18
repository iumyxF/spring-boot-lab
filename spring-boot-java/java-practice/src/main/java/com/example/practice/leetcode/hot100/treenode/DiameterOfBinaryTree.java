package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/18 9:52
 */
public class DiameterOfBinaryTree {

    /*
    给你一棵二叉树的根节点，返回该树的 直径 。
    二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
    两节点之间路径的 长度 由它们之间边数表示。

     */

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getDepth(root);
        return max;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ll = getDepth(root.left);
        int rl = getDepth(root.right);
        if (ll + rl > max) {
            max = ll + rl;
        }
        return Math.max(ll, rl) + 1;
    }
}