package com.example.practice.leetcode.hot100.traceback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2025/2/28 14:17
 */
public class LetterCombinations {

    /*
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    输入：digits = "23"
    输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */

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

    public List<String> letterCombinations(String digits) {
        ArrayList<String> res = new ArrayList<>();
        if (digits.length() < 1) {
            return res;
        }
        StringBuilder path = new StringBuilder();
        traceBack(digits, digits.length(), 0, path, res);
        return res;
    }

    public void traceBack(String digits, int length, int index, StringBuilder path, List<String> res) {
        if (path.length() == length) {
            res.add(new String(path));
            return;
        }
        for (int i = index; i < length; i++) {
            String[] strings = map.get(digits.charAt(i));
            if (null == strings) {
                continue;
            }
            for (int j = 0; j < strings.length; j++) {
                path.append(strings[j]);
                traceBack(digits, length, i + 1, path, res);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
}
