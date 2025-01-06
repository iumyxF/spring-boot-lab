package com.example.design.pattern.chain_of_responsibility_pattern.test02;

import com.example.design.pattern.chain_of_responsibility_pattern.test02.domain.OrderRequest;
import com.example.design.pattern.chain_of_responsibility_pattern.test02.handler.OrderHandler;

import java.util.ArrayList;

/**
 * @author fzy
 * @description:
 * @date 2024/10/10 13:54
 */
public class MainDemo {

    /**
     * 责任链模式的实际应用场景
     * 参考地址：<a href="https://mp.weixin.qq.com/s?__biz=MzIwNjYwNDQxMw==&mid=2247498324&idx=1&sn=13414b7a7d3c5295eb3a9e673a34aad2&chksm=9688dad6dc72c9b365ee8034a6871df84dfef489b68fe672737ceead172ea5b1a6c06640efb1&scene=126&sessionid=1728538999#rd">...</a>
     */
    public static void main(String[] args) {
        // 使用Springboot的话直接注入就行了,这里只是简单展示怎么使用
        ArrayList<OrderHandler> list = new ArrayList<>();
        ChainConfiguration configuration = new ChainConfiguration(list);
        OrderHandler orderHandler = configuration.orderChain();

        OrderRequest request = new OrderRequest();
        orderHandler.handle(request);
    }
}
