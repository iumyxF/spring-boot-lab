package com.example.practice.leetcode.word;

import java.util.*;

/**
 * @author fzy
 * @description:
 * @date 2024/11/26 9:39
 */
public class RandomPrintWord {

    public static void main(String[] args) {
        RandomPrintWord m = new RandomPrintWord();
        List<String> words = m.listWords();
        HashSet<Integer> set = new HashSet<>();
        boolean all = false;
        Random random = new Random();
        while (!all) {
            if (set.size() == words.size()) {
                all = true;
            }
            int i = random.nextInt(words.size());
            if (set.contains(i)) {
                continue;
            }
            System.out.println(words.get(i));
            set.add(i);
        }
    }

    public List<String> listWords() {
        ArrayList<String> list = new ArrayList<>();
        List<String> by20241126 = wordBy20241126();
        list.addAll(by20241126);
        return list;
    }

    /**
     * refusing disposable goods
     * additive
     * splash
     * metacognition
     * artware
     * offensive
     * dominate
     * humor
     */
    public List<String> wordBy20241126() {
        return Arrays.asList("拒绝一次性商品", "让人上瘾的", "泼",
                "元认知", "工艺品", "无礼的，冒犯的",
                "统治，支配", "幽默的");
    }

}
