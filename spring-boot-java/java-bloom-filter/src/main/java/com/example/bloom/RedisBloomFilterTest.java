package com.example.bloom;

/**
 * @author iumyx
 * @description: redis的布隆过滤器
 * @date 2023/10/7 14:04
 */
public class RedisBloomFilterTest {

    //使用redis的布隆过滤器
        /*
        给redis加载布隆过滤器的模块。这里直接使用docker镜像。参考https://github.com/RedisBloom/RedisBloom
        添加元素:BF.ADD [key] [value]
        查询元素:BF.EXISTS [key] [value] ,查询多个元素BF.EXISTS [key] [value1] [value2] [value3] ...
         */
    //使用redisson操作布隆过滤器，跳转到 spring-boot-redisson
}
