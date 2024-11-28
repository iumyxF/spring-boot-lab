package com.example.practice.leetcode.od.e_volume;

import java.util.*;

/**
 * @author feng
 * @description:
 * @date 2024/11/28 20:43
 */
public class E1001WanderingEarth {

    /**
     * <a href="https://blog.csdn.net/banxia_frontend/article/details/141278327">...</a>
     * <p>
     * 输入
     * 8 2
     * 0 2
     * 0 6
     * <p>
     * 解析
     * 1. 8 代表发动机总数 2代表计划手动启动的发动机总个数
     * 2. 0 时刻，编号2发动机启动
     * 3. 0 时刻，编号6发动机启动
     * <p>
     * 输出
     * 2
     * 0 4
     * 解析
     * 1. 最后启动的发动机数量是2
     * 2. 最后启动的发动机的编号
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 发动机的总个数
        int e = sc.nextInt(); // 计划手动启动的发动机总个数

        int[] engines = new int[n];
        Arrays.fill(engines, 0);

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < e; i++) {
            int t = sc.nextInt();
            int p = sc.nextInt();
            List<Integer> list = map.get(t);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(p);
            map.put(t, list);
        }
        solution(engines, map);
    }

    /**
     * @param engines        发动机
     * @param manualStartMap 手动启动的map key: 启动时刻 ， value: 启动的发动机编号
     */
    public static void solution(int[] engines, Map<Integer, List<Integer>> manualStartMap) {
        int time = 1;
        List<Integer> nowStartIndex = manualStartMap.get(0) == null ? new ArrayList<>() : manualStartMap.get(0);
        ArrayList<Integer> effectiveList = new ArrayList<>();
        int startEngCount = 0;
        while (time < engines.length) {
            if (nowStartIndex.size() > 0) {
                effectiveList.clear();
                for (Integer index : nowStartIndex) {
                    if (engines[index] != -1) {
                        engines[index] = -1;
                        startEngCount++;
                        effectiveList.add(index);
                    }
                }
                System.out.println(effectiveList);
            }
            if (startEngCount == engines.length) {
                System.out.println(effectiveList.size());
                StringBuilder sb = new StringBuilder();
                for (Integer index : effectiveList) {
                    sb.append(index).append(" ");
                }
                System.out.println(sb.substring(0, sb.length() - 1));
                return;
            }
            nowStartIndex = getNextStartIndex(time, manualStartMap, nowStartIndex, engines.length - 1);
            time++;
        }
    }

    public static List<Integer> getNextStartIndex(int time, Map<Integer, List<Integer>> manualStartMap, List<Integer> nowStartIndex, int maxLen) {
        HashSet<Integer> res = new HashSet<>();
        // 处理手动启动节点
        if (manualStartMap.containsKey(time)) {
            res.addAll(manualStartMap.get(time));
        }
        // 处理关联启动的节点
        for (Integer index : nowStartIndex) {
            if (index == 0) {
                res.add(index + 1);
                res.add(maxLen);
            } else if (index == maxLen) {
                res.add(0);
                res.add(maxLen - 1);
            } else {
                res.add(index - 1);
                res.add(index + 1);
            }
        }
        return new ArrayList<>(res);
    }
}
