package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fzy
 * @description: hard
 * @date 2024/7/10 15:26
 */
public class SolutionFindItinerary {

    //private LinkedList<String> res;
    //private LinkedList<String> temp = new LinkedList<>();
    //
    //public List<String> findItinerary(List<List<String>> tickets) {
    //    //res = new LinkedList<>();
    //    //temp = new LinkedList<>();
    //    tickets.sort(Comparator.comparing(a -> a.get(1)));
    //    temp.add("JFK");
    //    backtracking(tickets, new boolean[tickets.size()]);
    //    return res;
    //}
    //
    ///**
    // * @param tickets
    // * @param used
    // * @return 返回boolean 是为了尽快结束遍历
    // */
    //public boolean backtracking(List<List<String>> tickets, boolean[] used) {
    //    if (temp.size() == tickets.size() + 1) {
    //        res = new LinkedList<>(temp);
    //        return true;
    //    }
    //    for (int i = 0; i < tickets.size(); i++) {
    //        List<String> cur = tickets.get(i);
    //        if (!used[i] && cur.get(0).equals(temp.getLast())) {
    //            temp.add(cur.get(1));
    //            used[i] = true;
    //            if (backtracking(tickets, used)) {
    //                return true;
    //            }
    //            used[i] = false;
    //            temp.removeLast();
    //        }
    //    }
    //    return false;
    //}
    //
    ///**
    // * t1是否比t2高优先级（字典排序更前面）
    // */
    //public boolean isHighPriority(List<String> t1, List<String> t2) {
    //    for (int i = 0; i < t1.size(); i++) {
    //        String s1 = t1.get(i);
    //        String s2 = t2.get(i);
    //        if (!s1.equals(s2)) {
    //            return s1.compareTo(s2) < 0;
    //        }
    //    }
    //    return true;
    //}

    List<String> result = new ArrayList<>();
    LinkedList<String> path = new LinkedList<>();
    boolean[] used;

    public List<String> findItinerary(List<List<String>> tickets) {
        result = new ArrayList<>();
        path = new LinkedList<>();
        used = new boolean[tickets.size()];
        tickets.sort(Comparator.comparing(a -> a.get(1)));
        // 出发点是JFK
        path.add("JFK");
        backtracking(tickets);
        return result;
    }

    public boolean backtracking(List<List<String>> tickets) {
        if (path.size() == tickets.size() + 1) {
            result.addAll(path);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            // 树层剪枝，若之前已经发现相同机票无法找到答案，跳过。
            if (i > 0 && tickets.get(i).get(0).equals(tickets.get(i - 1).get(0))
                    && tickets.get(i).get(1).equals(tickets.get(i - 1).get(1))
                    && !used[i - 1]) {
                continue;
            }
            if (tickets.get(i).get(0).equals(path.getLast()) && !used[i]) {
                path.add(tickets.get(i).get(1));
                used[i] = true;
                if (backtracking(tickets)) {
                    return true;
                }
                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }


    /**
     * ["JFK","ADL","ANU","ADL","ANU","AUA","ADL","AUA","ADL","AUA","ANU","AXA","ADL","AUA","ANU","AXA","ADL","AXA","ADL","AXA","ANU","AXA","ANU","AXA","EZE","ADL","AXA","EZE","ADL","AXA","EZE","ADL","EZE","ADL","EZE","ADL","EZE","ANU","EZE","ANU","EZE","AUA","AXA","EZE","AUA","AXA","EZE","AUA","AXA","JFK","ADL","EZE","AUA","EZE","AXA","JFK","ADL","JFK","ADL","JFK","ADL","JFK","ADL","TIA","ADL","TIA","AUA","JFK","ANU","TIA","AUA","JFK","AUA","JFK","AUA","TIA","AUA","TIA","AXA","TIA","EZE","AXA","TIA","EZE","JFK","AXA","TIA","EZE","JFK","AXA","TIA","JFK","EZE","TIA","JFK","EZE","TIA","JFK","TIA","JFK","AUA","SYD"]
     *
     * @param args
     */
    public static void main(String[] args) {
        SolutionFindItinerary s = new SolutionFindItinerary();
        List<String> list1 = s.findItinerary(test1());
        System.out.println(list1);

        List<String> list2 = s.findItinerary(test2());
        System.out.println(list2);

        List<String> list3 = s.findItinerary(test3());
        System.out.println(list3);

    }

    public static List<List<String>> test1() {
        //["JFK","MUC","LHR","SFO","SJC"]
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("MUC");
        l1.add("LHR");
        ArrayList<String> l2 = new ArrayList<>();
        l2.add("JFK");
        l2.add("MUC");
        ArrayList<String> l3 = new ArrayList<>();
        l3.add("SFO");
        l3.add("SJC");
        ArrayList<String> l4 = new ArrayList<>();
        l4.add("LHR");
        l4.add("SFO");
        ArrayList<List<String>> tickets = new ArrayList<>();
        tickets.add(l1);
        tickets.add(l2);
        tickets.add(l3);
        tickets.add(l4);
        return tickets;
    }

    public static List<List<String>> test2() {
        //["JFK","ATL","JFK","SFO","ATL","SFO"]
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("JFK");
        l1.add("SFO");
        ArrayList<String> l2 = new ArrayList<>();
        l2.add("JFK");
        l2.add("ATL");
        ArrayList<String> l3 = new ArrayList<>();
        l3.add("SFO");
        l3.add("ATL");
        ArrayList<String> l4 = new ArrayList<>();
        l4.add("ATL");
        l4.add("JFK");
        ArrayList<String> l5 = new ArrayList<>();
        l5.add("ATL");
        l5.add("SFO");
        ArrayList<List<String>> tickets = new ArrayList<>();
        tickets.add(l1);
        tickets.add(l2);
        tickets.add(l3);
        tickets.add(l4);
        tickets.add(l5);
        return tickets;
    }

    public static List<List<String>> test3() {
        //测试用例:[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        //测试结果:["JFK","KUL"]
        //期望结果:["JFK","NRT","JFK","KUL"]
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("JFK");
        l1.add("KUL");
        ArrayList<String> l2 = new ArrayList<>();
        l2.add("JFK");
        l2.add("NRT");
        ArrayList<String> l3 = new ArrayList<>();
        l3.add("NRT");
        l3.add("JFK");
        ArrayList<List<String>> tickets = new ArrayList<>();
        tickets.add(l1);
        tickets.add(l2);
        tickets.add(l3);
        return tickets;
    }


}
