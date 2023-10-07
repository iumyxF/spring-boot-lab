package com.example.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author iumyx
 * @description: 布隆过滤器的使用
 * @date 2023/10/7 13:59
 */
public class GuavaBloomFilterTest {

    /**
     * Guava 提供的布隆过滤器的实现还是很不错的，
     * 但是它有一个重大的缺陷就是只能单机使用（另外，容量扩展也不容易），
     * 而现在互联网一般都是分布式的场景。为了解决这个问题，
     * 我们就需要用到 Redis 中的布隆过滤器了。
     */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        // 创建了一个最多存放 最多1500个整数的布隆过滤器，并且我们可以容忍误判的概率为百分之（0.01）
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
    }
}
