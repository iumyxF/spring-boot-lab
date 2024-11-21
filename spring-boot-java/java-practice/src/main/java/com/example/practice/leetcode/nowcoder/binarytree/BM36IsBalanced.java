package com.example.practice.leetcode.nowcoder.binarytree;

/**
 * @author fzy
 * @description:
 * @date 2024/11/21 11:14
 */
public class BM36IsBalanced {

    /*
    输入一棵节点数为二叉树，判该二叉树是否是平衡二叉树。
    在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
    平衡二叉树(Balanced Binary Tree),具有以下性质：它是一棵空树或它的左右两个子树的高度差的
    绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
     */

    public boolean isBalancedSolution(TreeNode pRoot) {
        if (pRoot == null) {
            return true;
        }
        int leftDepth = getDepth(pRoot.left);
        int rightDepth = getDepth(pRoot.right);
        if (leftDepth - rightDepth > 1 || rightDepth - leftDepth > 1) {
            return false;
        }
        return isBalancedSolution(pRoot.left) && isBalancedSolution(pRoot.right);
    }

    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
