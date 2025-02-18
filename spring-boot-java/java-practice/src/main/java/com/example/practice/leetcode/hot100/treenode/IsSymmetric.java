package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/18 9:33
 */
public class IsSymmetric {

    /*
    给你一个二叉树的根节点 root ， 检查它是否轴对称。
     */

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        return compareNode(root.left, root.right);
    }

    private boolean compareNode(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return compareNode(left.left, right.right) && compareNode(left.right, right.left);
        }
    }
}
