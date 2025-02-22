package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/22 10:42
 */
public class PathSum {

    /*
    给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

    路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     */

    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        dfs(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return res;
    }

    private void dfs(TreeNode root, int targetSum, long pathValue) {
        if (root == null) {
            return;
        }
        pathValue += root.val;
        // 不能直接return，因为不是二叉搜索树，数值是乱的
        if (pathValue == targetSum) {
            res++;
        }
        dfs(root.left, targetSum, pathValue);
        dfs(root.right, targetSum, pathValue);
    }
}
