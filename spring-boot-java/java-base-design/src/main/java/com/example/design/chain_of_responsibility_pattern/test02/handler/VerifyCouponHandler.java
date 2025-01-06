package com.example.design.chain_of_responsibility_pattern.test02.handler;

import com.example.design.chain_of_responsibility_pattern.test02.domain.OrderRequest;

/**
 * @author fzy
 * @description:
 * @date 2024/10/10 14:05
 */
public class VerifyCouponHandler extends OrderHandler{

    @Override
    public void handle(OrderRequest request) {
        // 业务方法
        System.out.println("订单优惠券验证处理...");
        // 传递
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}
