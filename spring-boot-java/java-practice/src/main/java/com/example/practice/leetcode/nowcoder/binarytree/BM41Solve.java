package com.example.practice.leetcode.nowcoder.binarytree;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/11/26 9:52
 */
public class BM41Solve {

    /*
    请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
    数据范围：0 <= n <= 10000
    要求：空间复杂度O(n),时间复杂度O(n)
    如输入[1,2,4,5,3][4,2,5,1,3]时，通过前序遍历的结果[1,2,4,5,3]和中序遍历的结果[4,2,5,1,3]可更建出二叉树
    输出右视图结果：[1,3,5]
     */

    public int[] solve(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0 || inOrder.length == 0) {
            return new int[]{};
        }
        TreeNode root = buildTree(preOrder, 0, preOrder.length, inOrder, 0, inOrder.length);
        return rightView(root);
    }

    /**
     * 区间 左闭右开
     */
    private TreeNode buildTree(int[] preOrder, int preLeft, int preRight, int[] inOrder, int inLeft, int inRight) {
        if (preLeft > preRight - 1) {
            return null;
        }
        int rootVal = preOrder[preLeft];
        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inOrder[i] == rootVal) {
                rootIndex = i;
            }
        }
        // 计算左节点数量
        int leftSize = rootIndex - inLeft;

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preOrder, preLeft + 1, preLeft + 1 + leftSize, inOrder, inLeft, rootIndex);
        root.right = buildTree(preOrder, preLeft + 1 + leftSize, preRight, inOrder, rootIndex + 1, inRight);

        return root;
    }

    private int[] rightView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        // 层序遍历
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.addLast(root);
        int loop = nodes.size();
        while (!nodes.isEmpty()) {
            for (int i = 0; i < loop; i++) {
                TreeNode node = nodes.pollFirst();
                if (i == loop - 1) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    nodes.addLast(node.left);
                }
                if (node.right != null) {
                    nodes.addLast(node.right);
                }
            }
            loop = nodes.size();
        }
        int[] res = new int[list.size()];
        int index = 0;
        for (Integer item : list) {
            res[index++] = item;
        }
        return res;
    }

    public static void main(String[] args) {
        BM41Solve s = new BM41Solve();
        //int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] pre = new int[]{1, 2, 4, 5, 3};
        //int[] inv = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        int[] inv = new int[]{4, 2, 5, 1, 3};
        System.out.println(Arrays.toString(s.solve2(pre, inv)));
    }

    /**
     * key = inOrder[i]
     * value = i
     */
    private Map<Integer, Integer> inOrderIndexMap = new HashMap<>();

    int index = 0;

    public int[] solve2(int[] preOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            inOrderIndexMap.put(inOrder[i], i);
        }
        TreeNode root = buildTree(preOrder, 0, inOrder.length - 1);
        ArrayList<Integer> list = new ArrayList<>();
        Deque<TreeNode> dq = new LinkedList<>();
        dq.add(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = dq.pollFirst();
                if (i == size - 1) {
                    list.add(tmp.val);
                }
                if (tmp.left != null) dq.add(tmp.left);
                if (tmp.right != null) dq.add(tmp.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private TreeNode buildTree(int[] preOrder, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootValue = preOrder[index++];
        TreeNode root = new TreeNode(rootValue);
        root.left = buildTree(preOrder, left, inOrderIndexMap.get(rootValue) - 1);
        root.right = buildTree(preOrder, inOrderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}
