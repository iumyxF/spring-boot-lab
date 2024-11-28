package com.example.practice.leetcode.od.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/28 15:59
 */
public class WordPuzzle {

    /*
    小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如 nesw，玩家需要猜出谜底库中正确的单词。
    猜中的要求如下:对于某个谜面和谜底单词，满足下面任一条件都表示猜中:
    1.变换顺序以后一样的，比如通过变换 w 和 e 的顺序，“nwes”跟“news”是可以完全对应的:
    2.字母去重以后是一样的，比如“woood”和“wood”是一样的，它们去重后都是“wod”
    请你写一个程序帮忙在谜底库中找到正确的谜底。谜面是多个单词，都需要找到对应的谜底，
    如果找不到的话，返回"not found"

    输入描述
    1.谜面单词列表，以","分隔
    2.谜底库单词列表，以","分隔

    输出描述
    匹配到的正确单词列表，以","分隔
    如果找不到，返回"not found!

    备注
    1.单词的数量 N 的范围: 0 < N < 1000
    2.词汇表的数量 M 的范围: 0< M< 1000
    3.单词的长度 P 的范围: 0< P < 20
    4.输入的字符只有小写英文字母，没有其他字符

    用例1
    输入
    conection
    connection,today
    输出
    connection

    用例2
    输入
    bdni,wooood
    bind,wrong,wood
    输出
    bind,wood


   分析：
   字符匹配，如果s1每个单词都和s2中的某个字符匹配了，那就返回该字符
     */
    public static void main(String[] args) {
        solution(Arrays.asList("conection"), Arrays.asList("connection", "today"));
        System.out.println("###");
        solution(Arrays.asList("bdni", "wooood"), Arrays.asList("bind", "wrong", "wood"));
    }

    public static void solution(List<String> targets, List<String> stringList) {
        StringBuilder res = new StringBuilder();
        for (String target : targets) {
            for (String s : stringList) {
                boolean match = match(target, s);
                if (match) {
                    res.append(s).append(",");
                    break;
                }
            }
        }
        if (res.length() == 0) {
            System.out.println("not found");
        } else {
            System.out.println(res.substring(0, res.length() - 1));
        }
    }

    public static boolean match(String target, String str) {
        if (target.length() == str.length()) {
            // 第一种匹配方式
            return match1(target, str);
        } else {
            return match2(target, str);
        }
    }

    private static boolean match2(String target, String str) {
        HashSet<String> tSet = new HashSet<>();
        HashSet<String> sSet = new HashSet<>();
        for (int i = 0; i < target.length(); i++) {
            tSet.add(String.valueOf(target.charAt(i)));
        }
        for (int i = 0; i < str.length(); i++) {
            sSet.add(String.valueOf(str.charAt(i)));
        }
        if (tSet.size() != sSet.size()) {
            return false;
        }
        for (String s : tSet) {
            if (!sSet.contains(s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean match1(String target, String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!target.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }
}
