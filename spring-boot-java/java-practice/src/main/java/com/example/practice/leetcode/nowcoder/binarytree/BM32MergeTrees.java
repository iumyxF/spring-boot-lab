package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.Stack;

/**
 * @author fzy
 * @description:
 * @date 2024/10/31 15:02
 */
public class BM32MergeTrees {

    /*
    已知两颗二叉树，将它们合并成一颗二叉树。
    合并规则是：都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替。

    层序遍历
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        Stack<TreeNode> s1 = new Stack<>();
        s1.push(t1);
        Stack<TreeNode> s2 = new Stack<>();
        s2.push(t2);
        TreeNode empty = new TreeNode(0);
        while (!s1.isEmpty()) {
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();

            n1.val += n2.val;

            if (n1.right != null && n2.right != null) {
                s1.push(n1.right);
                s2.push(n2.right);
            } else if (n1.right != null) {
                s1.push(n1.right);
                s2.push(empty);
            } else if (n2.right != null) {
                TreeNode temp = new TreeNode(0);
                n1.right = temp;
                s1.push(temp);
                s2.push(n2.right);
            }

            if (n1.left != null && n2.left != null) {
                s1.push(n1.left);
                s2.push(n2.left);
            } else if (n1.left != null) {
                s1.push(n1.left);
                s2.push(empty);
            } else if (n2.left != null) {
                TreeNode temp = new TreeNode(0);
                n1.left = temp;
                s1.push(temp);
                s2.push(n2.left);
            }
        }
        return t1;
    }
}
