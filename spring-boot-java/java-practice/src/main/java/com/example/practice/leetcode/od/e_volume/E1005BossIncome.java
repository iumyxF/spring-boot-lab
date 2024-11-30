package com.example.practice.leetcode.od.e_volume;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/11/29 14:19
 */
public class E1005BossIncome {

    public static Map<Integer, User> userMap;

    public static void main(String[] args) {
        // key - 对应的上级ID Map< key - 对应当前人员ID value 对应收入>
        Scanner in = new Scanner(System.in);
        int userCount = in.nextInt();
        userMap = new HashMap<>(userCount);
        int boos = Integer.MAX_VALUE;
        HashMap<Integer, List<User>> inputMap = new HashMap<>(userCount);
        for (int i = 0; i < userCount; i++) {
            int userId = in.nextInt();
            int bossId = in.nextInt();
            int income = in.nextInt();
            User user = new User(userId, income);
            userMap.put(userId, user);
            if (inputMap.containsKey(bossId)) {
                inputMap.get(bossId).add(user);
            } else {
                ArrayList<User> userList = new ArrayList<>();
                userList.add(user);
                inputMap.put(bossId, userList);
            }
            boos = Math.min(boos, bossId);
        }
        System.out.println(boos + " " + solution(0, inputMap));
    }

    public static int solution(Integer bossId, Map<Integer, List<User>> map) {
        int addPrice = 0;
        if (map.containsKey(bossId)) {
            List<User> users = map.get(bossId);
            for (User user : users) {
                addPrice += (solution(user.getId(), map) + user.getIncome()) / 100 * 15;
            }
        }
        return addPrice;
    }
}

class User {

    private Integer id;

    private Integer income;

    public User(Integer id, Integer income) {
        this.id = id;
        this.income = income;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIncome() {
        return income;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setIncome(Integer income) {
        this.income = income;
        return this;
    }
}