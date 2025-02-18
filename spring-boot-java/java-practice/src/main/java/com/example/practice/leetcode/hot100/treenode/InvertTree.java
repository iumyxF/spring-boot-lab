package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/18 9:29
 */
public class InvertTree {

    /*
    给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
