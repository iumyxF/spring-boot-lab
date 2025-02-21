package com.example.practice.leetcode.hot100.treenode;

/**
 * @author fzy
 * @description:
 * @date 2025/2/21 16:20
 */
public class BuildTree {

    /*
    105. 从前序与中序遍历序列构造二叉树

    给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

    输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    输出: [3,9,20,null,null,15,7]

    根左右
    左根右
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        int value = preorder[preLeft];
        int index = getInorderIndex(inorder, value);
        if (index == -1) {
            return null;
        }
        // 计算当前节点的左节点元素数量
        int leftNodeCount = index - inLeft;
        TreeNode root = new TreeNode(value);
        root.left = dfs(preorder, preLeft + 1, preLeft + leftNodeCount, inorder, inLeft, index);
        root.right = dfs(preorder, preLeft + leftNodeCount + 1, preRight, inorder, index + 1, inRight);
        return root;
    }

    private int getInorderIndex(int[] inorder, int value) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
