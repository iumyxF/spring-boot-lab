package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/24 13:54
 */
public class MaxPathSum {

    /*
    124. 二叉树中的最大路径和 hard

    二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
    该路径 至少包含一个 节点，且不一定经过根节点。
    路径和 是路径中各节点值的总和。

    给你一个二叉树的根节点 root ，返回其 最大路径和 。

    计算每个节点的最大左路径和最大右路径

    动态规划
    遍历方式 左右根，后序遍历
     */

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return maxSum;
    }

    private int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算左右路径最大值
        int leftPath = Math.max(maxPath(root.left), 0);
        int rightPath = Math.max(maxPath(root.right), 0);
        // 更新
        int value = leftPath + rightPath + root.val;
        if (value > maxSum) {
            maxSum = value;
        }
        return root.val + Math.max(leftPath, rightPath);
    }
}
