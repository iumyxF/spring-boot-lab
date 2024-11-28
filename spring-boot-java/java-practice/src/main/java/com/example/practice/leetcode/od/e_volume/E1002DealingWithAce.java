package com.example.practice.leetcode.od.e_volume;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author feng
 * @description:
 * @date 2024/11/28 22:09
 */
public class E1002DealingWithAce {

    /**
     * https://blog.csdn.net/banxia_frontend/article/details/141298145
     */

    public static void main(String[] args) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        // 扑克牌
        String[] split = input.split(" ");

        ArrayList<Integer> cards = new ArrayList<>(split.length);
        for (String s : split) {
            int cardValue = getCardValue(s);
            cards.add(cardValue);
        }
        cards.sort(Integer::compare);
        System.out.println("cards = " + cards);
        int slow = 0;
        int fast = 0;
        ArrayList<Integer> path = new ArrayList<>();
        while (slow < cards.size() - 5) {
            Integer value = cards.get(fast);
            if (path.isEmpty()) {
                path.add(value);
            } else {
                if (!isContinuity(path.get(path.size() - 1), value)) {
                    path.clear();
                    slow = fast;
                    continue;
                } else {
                    path.add(value);
                }
            }
            fast++;
            if (path.size() >= 5) {
                res.add(new ArrayList<>(path));
            }
        }
        if (res.size() == 0) {
            System.out.println("No");
        } else {
            for (List<Integer> list : res) {
                for (int i = 0; i < list.size(); i++) {
                    if (i < list.size() - 1) {
                        System.out.print(getCardByValue(list.get(i)) + " ");
                    } else {
                        System.out.println(getCardByValue(list.get(i)));
                    }
                }
            }
        }
    }

    public static int getCardValue(String card) {
        if (card.equals("A")) {
            return 14;
        } else if (card.equals("2")) {
            return 15;
        } else if (card.equals("J")) {
            return 11;
        } else if (card.equals("Q")) {
            return 12;
        } else if (card.equals("K")) {
            return 13;
        } else {
            return Integer.parseInt(card);
        }
    }

    public static String getCardByValue(int value) {
        if (value == 14) {
            return "A";
        } else if (value == 15) {
            return "2";
        } else if (value == 11) {
            return "J";
        } else if (value == 12) {
            return "Q";
        } else if (value == 13) {
            return "K";
        } else {
            return String.valueOf(value);
        }
    }

    /**
     * 判断两张牌是否连续
     */
    public static boolean isContinuity(Integer last, Integer now) {
        if (last == 13 && now == 14) {
            return false;
        } else if (last == 14 && now == 15) {
            return false;
        }
        return now - last == 1;
    }

}
