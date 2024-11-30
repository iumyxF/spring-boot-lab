package com.example.practice.leetcode.od.e_volume;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 14:46
 */
public class E1005Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取分销关系的数量
        int n = Integer.parseInt(sc.nextLine().trim());

        // 记录每个分销商的收入
        HashMap<Integer, Long> income = new HashMap<>();
        // 记录所有的分销商 ID
        HashSet<Integer> ids = new HashSet<>();
        // 记录子分销商到父分销商的映射关系
        HashMap<Integer, Integer> childToParent = new HashMap<>();
        // 记录父分销商到其所有子分销商的映射关系
        HashMap<Integer, LinkedList<Integer>> parentToChildren = new HashMap<>();

        // 读取输入数据并构建映射关系
        for (int i = 0; i < n; i++) {
            // 读取当前行
            String[] parts = sc.nextLine().split(" ");
            // 解析当前子分销商的 ID
            int childId = Integer.parseInt(parts[0]);
            // 解析当前子分销商的父分销商 ID
            int parentId = Integer.parseInt(parts[1]);
            // 解析当前子分销商的收入
            long childIncome = Long.parseLong(parts[2]);

            // 将子分销商的收入记录在 income 映射中
            income.put(childId, childIncome);
            // 将子分销商和父分销商的 ID 添加到分销商 ID 集合中
            ids.add(childId);
            ids.add(parentId);

            // 记录子分销商到父分销商的映射关系
            childToParent.put(childId, parentId);

            // 如果父分销商还没有子分销商列表，则初始化一个新的列表
            parentToChildren.putIfAbsent(parentId, new LinkedList<>());
            // 将当前子分销商 ID 添加到父分销商的子分销商列表中
            parentToChildren.get(parentId).add(childId);
        }

        // 寻找顶级分销商 (即没有父分销商的分销商，即 boss)
        for (int id : ids) {
            // 如果当前分销商 ID 不在 childToParent 映射中，说明它是顶级分销商
            if (!childToParent.containsKey(id)) {
                // 初始化顶级分销商的收入为0，因为它自身没有任何直接收入
                income.put(id, 0L);
                // 调用深度优先搜索算法计算该顶级分销商的总收入（包括来自下级分销商的提成）
                calcTotalIncome(id, parentToChildren, income);
                // 输出顶级分销商的 ID 和其计算出的总收入
                System.out.println(id + " " + income.get(id));
                // 一旦找到顶级分销商，结束循环
                break;
            }
        }
    }

    // 使用递归的深度优先搜索算法计算分销商的总收入，包括从下级分销商获取的部分
    private static void calcTotalIncome(int parentId,
                                        HashMap<Integer, LinkedList<Integer>> parentToChildren,
                                        HashMap<Integer, Long> income) {
        // 获取当前父分销商的子分销商列表
        LinkedList<Integer> children = parentToChildren.get(parentId);

        // 如果该父分销商有子分销商
        if (children != null && !children.isEmpty()) {
            // 遍历所有子分销商
            for (int childId : children) {
                // 递归计算子分销商的总收入
                calcTotalIncome(childId, parentToChildren, income);
                // 计算父分销商从该子分销商处获取的提成收入
                long additionalIncome = income.get(childId) / 100 * 15;
                // 将提成收入累加到父分销商的总收入中
                income.put(parentId, income.get(parentId) + additionalIncome);
            }
        }
    }
}