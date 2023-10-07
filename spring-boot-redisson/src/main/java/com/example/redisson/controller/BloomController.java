package com.example.redisson.controller;

import com.example.redisson.utils.RedisUtils;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iumyx
 * @description: 测试 redis 的布隆过滤器
 * @date 2023/10/7 15:06
 */
@RestController
public class BloomController {

    @GetMapping("/bloom")
    public void addBloomEntry() {
        RedissonClient client = RedisUtils.getClient();
        RBloomFilter<Object> bloomFilter = client.getBloomFilter("testFilter");
        //初始化布隆过滤器，预计元素一亿，误差率百分之2
        bloomFilter.tryInit(100000000L, 0.02);

        bloomFilter.add("java");
        bloomFilter.add("redis");
        bloomFilter.add("redisson");

        System.out.println(bloomFilter.contains("java"));
        System.out.println(bloomFilter.contains("redis"));
        System.out.println(bloomFilter.contains("github"));
    }
}