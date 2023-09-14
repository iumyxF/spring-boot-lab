package com.example.dynamic.application;

import com.example.dynamic.utils.AlgorithmUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author iumyxF
 * @description: LevenshteinDistance算法的应用
 * @date 2023/8/3 14:23
 */
class LevenshteinDistanceTest {

    /**
     * 脱敏数据和明文数据匹配
     * 脱敏数据：张*狗     123****8910     123456****8765****
     * 明文数据：张大狗     12345678910     123456789987654321
     * 要把两份数据进行匹配，得出上面两条数据对应的是同一个人的数据，原理就是：当且仅当两条数据中手机号的LD值为4，身份证的LD值为8，姓名的LD值为1，则两条数据完全匹配。
     */
    @Test
    void matchingOfDesensitisedAndPlaintextData() {
        String sourceName = "张*狗";
        String sourcePhone = "123****8910";
        String sourceIdentityNo = "123456****8765****";
        String targetName = "张大狗";
        String targetPhone = "12345678910";
        String targetIdentityNo = "123456789987654321";

        boolean match = AlgorithmUtils.ld(sourceName, targetName) == 1
                && AlgorithmUtils.ld(sourcePhone, targetPhone) == 4
                && AlgorithmUtils.ld(sourceIdentityNo, targetIdentityNo) == 8;

        System.out.println("[test-1]是否匹配:" + match);

        targetName = "张大doge";
        boolean match2 = AlgorithmUtils.ld(sourceName, targetName) == 1
                && AlgorithmUtils.ld(sourcePhone, targetPhone) == 4
                && AlgorithmUtils.ld(sourceIdentityNo, targetIdentityNo) == 8;

        System.out.println("[test-2]是否匹配:" + match2);
    }

    /**
     * 词典应用的拼写提示
     * 例如输入了throwab，就能提示出throwable，
     * 笔者认为一个简单实现就是遍历t开头的单词库，
     * 寻找匹配度比较高（LD值比较小）的单词进行提示（实际上为了满足效率有可能并不是这样实现的）
     */
    @Test
    void spellCheck() {
        String target = "chenxiaoqi";
        // 模拟一个单词库
        List<String> words = new ArrayList<>();
        words.add("cxq");
        words.add("chen");
        words.add("xq");
        words.add("xiaoqi");
        Map<String, BigDecimal> result = new HashMap<>(1);
        words.forEach(x -> result.put(x, AlgorithmUtils.mr(x, target)));
        System.out.println("输入值为:" + target);
        result.forEach((k, v) -> System.out.printf("候选值:%s,匹配度:%s%n", k, v));
    }
}