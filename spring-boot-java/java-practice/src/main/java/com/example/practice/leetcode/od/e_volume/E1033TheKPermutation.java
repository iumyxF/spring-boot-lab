package com.example.practice.leetcode.od.e_volume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author fzy
 * @description:
 * @date 2024/11/30 9:45
 */
public class E1033TheKPermutation {

    /**
     * https://blog.csdn.net/banxia_frontend/article/details/141884907
     * 1. 213
     * 2. 21
     * 3. 1432
     * 4. 12345
     * 5. 123654
     * 6. 987654321 特别慢
     * 7. 7654321
     * 8. 87654321
     * 9. 987654312 特别慢
     * 10. 654321
     */

    public static List<String> list;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int size = in.nextInt();
        list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(size);
        for (int i = 1; i <= n; i++) {
            sb.append(i);
        }
        solution(sb, size);
    }

    public static void solution(StringBuilder sb, int size) {
        boolean[] used = new boolean[sb.length()];
        dfs(sb, used, new StringBuilder(size), size);
        Collections.sort(list);
        System.out.println(list.get(size - 1));
    }

    public static void dfs(StringBuilder str, boolean[] used, StringBuilder sb, int size) {
        if (sb.length() == str.length()) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            sb.append(str.charAt(i));
            dfs(str, used, sb, size);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
            if (list.size() >= size) {
                break;
            }
        }
    }
}
