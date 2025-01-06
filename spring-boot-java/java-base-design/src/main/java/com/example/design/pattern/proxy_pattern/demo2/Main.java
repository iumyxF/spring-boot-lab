package com.example.design.pattern.proxy_pattern.demo2;

/**
 * @author fzy
 * @description:
 * @date 2024/6/28 10:31
 */
public class Main {

    public static void main(String[] args) {
        ProxySearcher searcher = new ProxySearcher();
        String search = searcher.DoSearch("杨过", "玉女心经");
        System.out.println(search);
    }
}
