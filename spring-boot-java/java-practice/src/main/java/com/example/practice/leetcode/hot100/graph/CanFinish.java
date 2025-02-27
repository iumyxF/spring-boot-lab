package com.example.practice.leetcode.hot100.graph;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/2/26 17:13
 */
public class CanFinish {

    /*
    你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

    在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
    表示如果要学习课程 ai 则 必须 先学习课程  bi 。

    例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
    请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

    输入：numCourses = 2, prerequisites = [[1,0]]
    输出：true
    解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

    输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
    输出：false
    解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。

    1. 用map记录前置和可学课程，用数组统计每个课程需要的前置课程数量
    2. 用队列统计已学课程
    3. 遍历队列，去减少数组的值
    4. 判断数组中所有课程是否都为0了
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 前置和可学课程
        HashMap<Integer, List<Integer>> map = new HashMap<>(prerequisites.length);
        // 个课程需要的前置课程数量
        int[] preClassCount = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            List<Integer> list = map.getOrDefault(prerequisite[1], new ArrayList<>());
            list.add(prerequisite[0]);
            map.put(prerequisite[1], list);

            preClassCount[prerequisite[0]]++;
        }
        // 记录已经学习的课程
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < preClassCount.length; i++) {
            if (preClassCount[i] == 0) {
                linkedList.add(i);
            }
        }

        // 遍历以学课程，判断能学习什么课程
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                Integer poll = linkedList.poll();
                List<Integer> list = map.get(poll);
                if (null == list) {
                    continue;
                }
                for (Integer item : list) {
                    preClassCount[item]--;
                    if (preClassCount[item] == 0) {
                        linkedList.addLast(item);
                    }
                }
            }
        }

        // 判断所有课程是否学完
        for (int item : preClassCount) {
            if (item != 0) {
                return false;
            }
        }
        return true;
    }
}
