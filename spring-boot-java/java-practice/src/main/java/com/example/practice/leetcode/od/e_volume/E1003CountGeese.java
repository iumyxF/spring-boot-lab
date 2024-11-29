package com.example.practice.leetcode.od.e_volume;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 9:04
 */
public class E1003CountGeese {

    /**
     * https://blog.csdn.net/banxia_frontend/article/details/141387588
     * <p>
     * quack
     */

    public static void main(String[] args) {
        solution("quackquack"); // 1
        solution("quac"); // -1
        solution("quacqkuackquack"); // 2
        solution("qququaauqccauqkkcauqqkcauuqkcaaukccakkck"); // 5
        solution("quackquuackqquackac"); // 1
        solution("quackqauckquack");// 1
        solution("qquuaaccckk"); // 2
        solution("kkkcccuaaq");// -1
        solution("quacqkuquacqkacuqkackuack");//3
        solution("q");// -1
    }

    public static void solution(String str) {
        // 能组成字母的下标列表
        ArrayList<List<Integer>> quackList = new ArrayList<>();
        String[] template = new String[]{"q", "u", "a", "c", "k"};
        int templateIndex = 0;

        int left = findStart(0, str);
        int right = left;
        boolean[] used = new boolean[str.length()];
        ArrayList<Integer> path = new ArrayList<>();
        while (left < str.length() - 5 && right < str.length()) {
            String at = String.valueOf(str.charAt(right));
            if (template[templateIndex].equals(at) && !used[right]) {
                // 保存路径
                path.add(right);
                used[right] = true;
                templateIndex++;

                if (templateIndex == template.length) {
                    quackList.add(new ArrayList<>(path));
                    // 重置
                    path.clear();
                    templateIndex = 0;
                    left = findStart(left + 1, str);
                    right = left;
                    continue;
                }
            }
            right++;
        }
        int num = getRes(quackList);
        System.out.println(num);
    }

    private static int getRes(ArrayList<List<Integer>> quackList) {
        if (quackList.size() == 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < quackList.size(); i++) {
            List<Integer> list = quackList.get(i);
            Integer lastIndex = list.get(list.size() - 1);
            int num = 0;
            for (int j = i + 1; j < quackList.size(); j++) {
                List<Integer> list1 = quackList.get(j);
                Integer startIndex = list1.get(0);
                if (startIndex < lastIndex) {
                    num++;
                } else {
                    break;
                }
            }
            max = Math.max(num, max);
        }
        return max + 1;
    }

    public static int findStart(int start, String str) {
        for (int i = start; i < str.length(); i++) {
            if (str.charAt(i) == 'q') {
                return i;
            }
        }
        return str.length() - 1;
    }

}
