package com.example.design.pattern.chain_of_responsibility_pattern.test02;

import com.example.design.pattern.chain_of_responsibility_pattern.test02.handler.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fzy
 * @description: 责任链配置
 * @date 2024/10/10 13:56
 */
public class ChainConfiguration {

    private List<String> steps;

    private final Map<String, OrderHandler> handlerMap = new HashMap<>();

    public ChainConfiguration(List<OrderHandler> handlers) {
        // 初始化处理器映射
        handlerMap.put("orderValidationHandler", handlers.stream().filter(h -> h instanceof OrderValidationHandler).findFirst().orElse(null));
        handlerMap.put("verifyCouponHandler", handlers.stream().filter(h -> h instanceof VerifyCouponHandler).findFirst().orElse(null));
        handlerMap.put("shippingFeeHandler", handlers.stream().filter(h -> h instanceof ShippingFeeHandler).findFirst().orElse(null));
        handlerMap.put("totalAmountHandler", handlers.stream().filter(h -> h instanceof TotalAmountHandler).findFirst().orElse(null));
        handlerMap.put("processPaymentHandler", handlers.stream().filter(h -> h instanceof ProcessPaymentHandler).findFirst().orElse(null));
    }

    /**
     * 构造处理链
     *
     * @return OrderHandler
     */
    public OrderHandler orderChain() {
        if (steps == null || steps.isEmpty()) {
            throw new IllegalArgumentException("处理链步骤不能为空");
        }
        OrderHandler firstHandler = null;
        OrderHandler lastHandler = null;
        for (String step : steps) {
            OrderHandler handler = handlerMap.get(step);
            if (handler == null) {
                throw new IllegalArgumentException("未找到处理器: " + step);
            }
            if (firstHandler == null) {
                firstHandler = handler;
            }
            if (lastHandler != null) {
                lastHandler.setNextHandler(handler);
            }
            lastHandler = handler;
        }
        if (lastHandler != null) {
            // 最后一个处理器的 nextHandler 设置为 null
            lastHandler.setNextHandler(null);
        }
        return firstHandler;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }
}
