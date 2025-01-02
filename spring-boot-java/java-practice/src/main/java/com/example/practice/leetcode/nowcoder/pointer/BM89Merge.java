package com.example.practice.leetcode.nowcoder.pointer;

import java.util.ArrayList;

/**
 * @author fzy
 * @description:
 * @date 2/1/2025 上午9:58
 */
public class BM89Merge {

    /*
    给出一组区间，请合并所有重叠的区间。
    请保证合并后的区间按区间起点升序排列。

    给出一组区间，请合并所有重叠的区间。
    请保证合并后的区间按区间起点升序排列。

    input  [[10,30],[20,60],[80,100],[150,180]]
    output [[10,60],[80,100],[150,180]]
     */

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }
        intervals.sort((o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);
        ArrayList<Interval> res = new ArrayList<>(intervals.size());
        res.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval pre = res.get(res.size() - 1);
            Interval cur = intervals.get(i);
            if (pre.end >= cur.start) {
                if (pre.end < cur.end) {
                    pre.end = cur.end;
                }
            } else {
                res.add(cur);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        BM89Merge s = new BM89Merge();
        ArrayList<Interval> intervals = new ArrayList<>();
        // [[10,30],[20,60],[80,100],[150,180]]
        //intervals.add(new Interval(10, 30));
        //intervals.add(new Interval(20, 60));
        //intervals.add(new Interval(80, 100));
        //intervals.add(new Interval(150, 180));

        //intervals.add(new Interval(150, 180));
        //intervals.add(new Interval(80, 100));
        //intervals.add(new Interval(20, 60));
        //intervals.add(new Interval(10, 30));

        // [[10,30],[20,60],[10,20],[50,100]]
        //intervals.add(new Interval(10, 30));
        //intervals.add(new Interval(20, 60));
        //intervals.add(new Interval(10, 20));
        //intervals.add(new Interval(50, 100));

        //[[1,4],[0,0]]
        //intervals.add(new Interval(1, 4));
        //intervals.add(new Interval(0, 0));

        // [[2,3],[4,5],[6,7],[8,9],[1,10]]
        //intervals.add(new Interval(2, 3));
        //intervals.add(new Interval(4, 5));
        //intervals.add(new Interval(6, 7));
        //intervals.add(new Interval(8, 9));
        //intervals.add(new Interval(1, 10));

        //intervals.add(new Interval(1, 1000));
        //intervals.add(new Interval(2, 1000));
        //intervals.add(new Interval(3, 1000));
        //intervals.add(new Interval(4, 1000));
        //intervals.add(new Interval(5, 1000));

        //[[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]]
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(2, 2));
        intervals.add(new Interval(3, 3));
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(5, 7));
        intervals.add(new Interval(2, 2));
        intervals.add(new Interval(4, 6));

        s.merge(intervals).forEach(System.out::println);
    }
}

class Interval {

    /**
     * 起点
     */
    int start;

    /**
     * 终点
     */
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}