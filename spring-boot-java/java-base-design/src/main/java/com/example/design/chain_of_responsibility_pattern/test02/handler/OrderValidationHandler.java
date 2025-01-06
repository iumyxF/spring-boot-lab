package com.example.design.chain_of_responsibility_pattern.test02.handler;

import com.example.design.chain_of_responsibility_pattern.test02.domain.OrderRequest;

/**
 * @author fzy
 * @description:
 * @date 2024/10/10 14:04
 */
public class OrderValidationHandler extends OrderHandler {

    @Override
    public void handle(OrderRequest request) {

        // 业务方法
        System.out.println("订单校验处理...");
        // 传递
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
