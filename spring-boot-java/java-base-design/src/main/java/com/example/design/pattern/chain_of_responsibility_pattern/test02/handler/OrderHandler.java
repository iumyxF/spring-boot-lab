package com.example.design.pattern.chain_of_responsibility_pattern.test02.handler;

import com.example.design.pattern.chain_of_responsibility_pattern.test02.domain.OrderRequest;

/**
 * @author fzy
 * @description:
 * @date 2024/10/10 14:02
 */
public abstract class OrderHandler {

    /**
     * 一个下个处理器对象
     */
    protected OrderHandler nextHandler;

    public void setNextHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(OrderRequest request);
}
