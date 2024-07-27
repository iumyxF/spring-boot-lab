package com.example.practice.leetcode.test.mic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/7/25 13:43
 */
public class MicTestMain {

    private static HashMap<String, UnitDto> UNIT_MAP = new HashMap<>();

    /**
     * 0 - notOpen
     * 1 - open
     * 2 - wait
     */
    static {
        UNIT_MAP.put("101", new UnitDto(101, 0));
        UNIT_MAP.put("102", new UnitDto(102, 0));
        UNIT_MAP.put("103", new UnitDto(103, 0));
        UNIT_MAP.put("104", new UnitDto(104, 0));
        UNIT_MAP.put("105", new UnitDto(105, 0));
    }

    public void updateStatus(List<Integer> openList, List<Integer> waitingList) {
        UNIT_MAP.forEach((id, unit) -> {
            if (openList.contains(Integer.valueOf(id))) {
                if (unit.getStatus() != 1) {
                    unit.setStatus(1);
                    System.out.println("update id = " + id + " opening");
                }
            } else if (waitingList.contains(Integer.valueOf(id))) {
                if (unit.getStatus() != 2) {
                    unit.setStatus(2);
                    System.out.println("update id = " + id + " waiting");
                }
            } else {
                if (unit.getStatus() != 0) {
                    unit.setStatus(0);
                    System.out.println("update id = " + id + " close");
                }
            }
        });
        System.out.println();
    }


    public static void main(String[] args) {
        MicTestMain main = new MicTestMain();
        main.updateStatus(Collections.singletonList(101), Collections.emptyList());
        main.updateStatus(Arrays.asList(101, 102, 103, 104), Collections.emptyList());
        main.updateStatus(Collections.singletonList(102), Collections.emptyList());
        main.updateStatus(Collections.emptyList(), Collections.emptyList());
    }
}
