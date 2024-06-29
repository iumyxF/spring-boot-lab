package com.example.practice.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author iumyxF
 * @description: lc-17 电话号码的字母组合
 * @date 2024/6/29 8:49
 */
public class SolutionLetterCombinations {

    static HashMap<Character, String[]> map = new HashMap<>();

    static {
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});
    }

    ArrayList<String> res = new ArrayList<>();

    StringBuilder temp = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        if (digits.length() < 1) {
            return new ArrayList<>();
        }
        backtracking(digits, 0);
        return res;
    }

    /**
     * 1. 终止条件 temp.size == array.length
     */
    public void backtracking(String digits, int index) {
        if (temp.length() == digits.length()) {
            res.add(new String(temp));
            return;
        }
        for (int i = index; i < digits.length(); i++) {
            String[] array = map.get(digits.charAt(i));
            for (int j = 0; j < array.length; j++) {
                temp.append(array[j]);
                backtracking(digits, i + 1);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        SolutionLetterCombinations s = new SolutionLetterCombinations();
        //System.out.println(s.letterCombinations("23"));
        //System.out.println(s.letterCombinations(""));
        System.out.println(s.letterCombinations("2"));
    }
}
