package com.example.practice.leetcode.od.e_volume;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 15:24
 */
public class E1006Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取谜面单词列表，以逗号分隔
        String[] puzzleWords = scanner.nextLine().split(",");
        // 读取谜底库单词列表，以逗号分隔
        String[] answerBank = scanner.nextLine().split(",");
        // 创建一个列表，用于存储匹配到的正确单词
        List<String> matchedAnswers = new ArrayList<>();

        // 遍历谜面单词列表
        for (String puzzleWord : puzzleWords) {
            // 去除谜面单词中的重复字母，TreeSet自动升序排序
            String puzzleWordNoDuplicate = String.join("", new TreeSet<>(Arrays.asList(puzzleWord.split(""))));
            // 标记是否匹配到对应的谜底
            boolean isFound = false;

            // 遍历谜底库单词列表
            for (String answer : answerBank) {
                // 去除谜底单词中的重复字母，TreeSet自动升序排序
                String answerNoDuplicate = String.join("", new TreeSet<>(Arrays.asList(answer.split(""))));

                // 如果去重后的谜面单词与谜底单词相同，则将谜底单词添加到结果列表中，并将 isFound 标记为 true
                if (puzzleWordNoDuplicate.equals(answerNoDuplicate)) {
                    matchedAnswers.add(answer);
                    isFound = true;
                }
            }

            // 如果没有找到匹配的单词，则将 "not found" 添加到结果列表中
            if (!isFound) {
                matchedAnswers.add("not found");
            }
        }

        // 输出匹配到的正确单词列表，以逗号分隔
        System.out.println(String.join(",", matchedAnswers));
    }


}
