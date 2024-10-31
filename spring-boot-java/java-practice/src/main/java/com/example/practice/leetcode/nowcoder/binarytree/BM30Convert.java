package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/31 10:18
 */
public class BM30Convert {

    /*
    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。如下图所示

    数据范围：输入二叉树的节点数0 <= n <= 1000,二叉树中每个节点的值0 <= val <= 1000
    要求：空间复杂度O(1)(即在原树上操作)，时间复杂度O(n)
    注意
    1. 要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
    2. 返回链表中的第一个节点的指针
    3. 函数返回的TreeNode,有左右指针，其实可以看成一个双向链表的数据结构
    4. 你不用输出双向链表，程序会根据你的返回值自动打印输出
    输入描述：二叉树的根节点
    返回值描述：双向链表的其中一个头节点。

    二叉搜索树：左节点<根节点 右节点>根节点
    中序遍历 左根右
     */

    public TreeNode convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        boolean isFirst = true;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (isFirst) {
                root = cur;
                pre = cur;
                isFirst = false;
            } else {
                pre.right = cur;
                cur.left = pre;
                pre = cur;
            }
            cur = cur.right;
        }
        return root;
    }

    public TreeNode convertDfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        // 1.将左子树构造成双链表，并返回链表头节点
        TreeNode left = convertDfs(root.left);
        TreeNode p = left;
        // 2.定位至左子树双链表最后一个节点
        while (p != null && p.right != null) {
            p = p.right;
        }
        // 3.如果左子树链表不为空的话，将当前root追加到左子树链表
        if (left != null) {
            p.right = root;
            root.left = p;
        }
        // 4.将右子树构造成双链表，并返回链表头节点
        TreeNode right = convertDfs(root.right);
        // 5.如果右子树链表不为空的话，将该链表追加到root节点之后
        if (right != null) {
            right.left = root;
            root.right = right;
        }
        return left != null ? left : root;
    }

    public static void main(String[] args) {
        TreeNode n10 = new TreeNode(10);
        TreeNode n6 = new TreeNode(6);
        TreeNode n14 = new TreeNode(14);
        TreeNode n4 = new TreeNode(4);
        TreeNode n8 = new TreeNode(8);
        TreeNode n12 = new TreeNode(12);
        TreeNode n16 = new TreeNode(16);

        n10.left = n6;
        n10.right = n14;
        n6.left = n4;
        n6.right = n8;
        n14.left = n12;
        n14.right = n16;

        BM30Convert s = new BM30Convert();
        s.convert(n10);
    }
}
