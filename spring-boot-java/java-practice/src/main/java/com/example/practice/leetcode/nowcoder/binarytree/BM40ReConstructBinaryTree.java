package com.example.practice.leetcode.nowcoder.binarytree;

/**
 * @author fzy
 * @description:
 * @date 2024/11/25 15:11
 */
public class BM40ReConstructBinaryTree {


    /*
    给定节点数为的二叉树的[前序遍历]和[中序遍历]结果，请重建出该二叉树并返回它的头结点。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}

    提示
    1.vin.length = pre.length
    2.pre 和 vin 均无重复元素
    3.vin 出现的元素均出现在 pre 里
    4.只需要返回根结点，系统会自动输出整颗树做答案对比
    数据范围：n <= 2000,节点的值 -10000 < val < 10000
    要求：空间复杂度O(n),时间复杂度O(n)


    分析：
    1. 前序 根左右; 中序 左根右


    注意区间范围，定义左闭右开区间，差一点点做出来了可惜

     */
    public TreeNode reConstructBinaryTree(int[] preOrder, int[] vinOrder) {
        if (preOrder == null || preOrder.length == 0
                || vinOrder == null || vinOrder.length == 0) {
            return null;
        }
        return buildTree(preOrder, vinOrder, 0, preOrder.length, 0, vinOrder.length);
    }


    public TreeNode buildTree(int[] preOrder, int[] vinOrder, int preLeft, int preRight, int vinLeft, int vinRight) {
        if (preLeft > preRight - 1) {
            return null;
        }
        int rootVal = preOrder[preLeft];

        int rootIndex = -1;
        for (int i = vinLeft; i < vinRight; i++) {
            if (vinOrder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        TreeNode root = new TreeNode(rootVal);
        int leftSize = rootIndex - vinLeft;

        root.left = buildTree(preOrder, vinOrder, preLeft + 1, preLeft + leftSize + 1, vinLeft, rootIndex);
        root.right = buildTree(preOrder, vinOrder, preLeft + leftSize + 1, preRight, rootIndex + 1, vinRight);
        return root;
    }

    public static void main(String[] args) {
        BM40ReConstructBinaryTree s = new BM40ReConstructBinaryTree();
        // [1,2,4,7,3,5,6,8],[4,7,2,1,5,3,8,6]
        int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] inv = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode node = s.reConstructBinaryTree(pre, inv);
        System.out.println();
    }
}
