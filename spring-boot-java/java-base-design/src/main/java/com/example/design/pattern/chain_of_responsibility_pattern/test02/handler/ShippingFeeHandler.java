package com.example.design.pattern.chain_of_responsibility_pattern.test02.handler;

import com.example.design.pattern.chain_of_responsibility_pattern.test02.domain.OrderRequest;

/**
 * @author fzy
 * @description:
 * @date 2024/10/10 14:05
 */
public class ShippingFeeHandler extends OrderHandler{

    @Override
    public void handle(OrderRequest request) {
        // 业务方法
        System.out.println("订单模拟运费计算逻辑...");
        // 传递
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
