package com.example.practice.leetcode.od.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author feng
 * @description:
 * @date 2024/12/7 23:30
 */
public class Technology1 {
/*
    题目描述
    2012伦敦奥运会即将到来，大家都非常关注奖牌榜的情况，现在我们假设奖牌榜的排名规则如下：

    1、首先gold medal数量多的排在前面；
    2、其次silver medal数量多的排在前面；
    3、然后bronze medal数量多的排在前面；
    4、若以上三个条件仍无法区分名次，则以国家名称的字典序排定。
    我们假设国家名称不超过20个字符、各种奖牌数不超过100，且大于等于0。

    解答要求
    时间限制：1000ms, 内存限制：100MB

    输入
    第一行输入一个整数N(0<N<21)，代表国家数量;
    然后接下来的N行，每行包含一个字符串Name i表示每个国家的名称，
    和三个整数Gi、Si、Bi表示每个获得的gold medal、silver medal、bronze medal的数量，
    以空格隔开，如(China 51 20 21)，具体见样例输入。

    输出
    输出奖牌榜的依次顺序，只输出国家名称，各占一行，具体见样例输出。

    样例
    输入样例 1 复制

    5
    China 32 28 34
    England 12 34 22
    France 23 33 2
    Japan 12 34 25
    Rusia 23 43 0

    输出样例 1
    China
    Rusia
    France
    Japan
    England
    提示样例 1
     */

    public static List<String> solution(List<Country> countryList) {
        ArrayList<Country> res = new ArrayList<>(countryList);
        for (int i = 1; i < res.size(); i++) {
            Country base = res.get(i);
            int j = i - 1;
            while (j >= 0 && res.get(j).compareTo(base) > 0) {
                swap(res, j, j + 1);
                j--;
            }
            res.set(j + 1, base);
        }
        ArrayList<String> temp = new ArrayList<>(res.size());
        for (int i = res.size() - 1; i >= 0; i--) {
            temp.add(res.get(i).getName());
        }
        return temp;
    }


    private static void swap(ArrayList<Country> res, int j, int i) {
        Country temp = res.get(j);
        res.set(j, res.get(i));
        res.set(i, temp);
    }

    public static void main(String[] args) {

        // String[] c = new String[]{"China", "England", "France", "Japan", "Rusia"};
        // int[][] arr = new int[5][3];
        // arr[0] = new int[]{32, 28, 34};
        // arr[1] = new int[]{12, 34, 22};
        // arr[2] = new int[]{23, 33, 2};
        // arr[3] = new int[]{12, 34, 25};
        // arr[4] = new int[]{23, 43, 0};
        Country c1 = new Country("China", 32, 28, 34);
        Country c2 = new Country("England", 12, 34, 22);
        Country c3 = new Country("France", 23, 33, 2);
        Country c4 = new Country("Japan", 12, 34, 25);
        Country c5 = new Country("Rusia", 23, 43, 0);
        System.out.println(solution(Arrays.asList(c1, c2, c3, c4, c5)));
    }
}

class Country implements Comparable<Country> {

    private String name;

    private Integer g;

    private Integer s;

    private Integer b;

    public Country(String name, Integer g, Integer s, Integer b) {
        this.name = name;
        this.g = g;
        this.s = s;
        this.b = b;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getG() {
        return g;
    }

    public Country setG(Integer g) {
        this.g = g;
        return this;
    }

    public Integer getS() {
        return s;
    }

    public Country setS(Integer s) {
        this.s = s;
        return this;
    }

    public Integer getB() {
        return b;
    }

    public Country setB(Integer b) {
        this.b = b;
        return this;
    }

    @Override
    public int compareTo(Country o) {
        if (g > o.getG()) {
            return 1;
        } else if (g < o.getG()) {
            return -1;
        }
        if (s > o.getS()) {
            return 1;
        } else if (g < o.getS()) {
            return -1;
        }
        if (b > o.getB()) {
            return 1;
        } else if (b < o.getB()) {
            return -1;
        }
        int len = Math.max(name.length(), o.getName().length());
        for (int i = 0; i < len; i++) {
            if (name.charAt(i) < o.getName().charAt(i)) {
                return 1;
            } else if (name.charAt(i) > o.getName().charAt(i)) {
                return -1;
            }
        }
        return 0;
    }
}
