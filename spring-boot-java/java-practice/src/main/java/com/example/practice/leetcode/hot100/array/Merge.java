package com.example.practice.leetcode.hot100.array;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2025/2/6 17:11
 */
public class Merge {

    /*
    以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start(i), end(i)] 。
    请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

    1 <= intervals.length <= 10^4
    intervals[i].length == 2
    0 <= start(i) <= end(i) <= 10^4

    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出：[[1,6],[8,10],[15,18]]
    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

    输入：intervals = [[1,4],[4,5]]
    输出：[[1,5]]
    解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     */

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        ArrayList<int[]> resList = new ArrayList<>();
        resList.add(intervals[0]);
        int index = 1;
        // 如果 num[i-1][1] > nums[i][0] 把num[i-1][1]的值变成 nums[i][1]
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = resList.get(index - 1);
            int[] cur = intervals[i];
            if (pre[1] >= cur[0]) {
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                resList.add(cur);
                index++;
            }
        }
        int[][] res = new int[resList.size()][2];
        for (int i = 0; i < resList.size(); i++) {
            int[] ints = resList.get(i);
            res[i][0] = ints[0];
            res[i][1] = ints[1];
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        // [[1,3],[2,6],[8,10],[15,18]]
        //list.add(new int[]{1, 3});
        //list.add(new int[]{2, 6});
        //list.add(new int[]{8, 10});
        //list.add(new int[]{15, 18});

        // [[1,4],[2,3]]
        list.add(new int[]{1, 4});
        list.add(new int[]{2, 3});

        int[][] intervals = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            intervals[i][0] = list.get(i)[0];
            intervals[i][1] = list.get(i)[1];
        }
        Merge s = new Merge();
        int[][] merge = s.merge(intervals);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
