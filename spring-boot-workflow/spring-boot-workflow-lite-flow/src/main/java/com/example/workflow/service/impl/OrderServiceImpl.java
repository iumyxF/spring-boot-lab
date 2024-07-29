package com.example.workflow.service.impl;

import com.example.workflow.model.Order;
import com.example.workflow.nodes.OrderContext;
import com.example.workflow.service.OrderService;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/7/27 14:20
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private FlowExecutor flowExecutor;

    @Override
    public Long calculateCost(Order order) {
        OrderContext orderContext = new OrderContext(order);
        LiteflowResponse resp = flowExecutor.execute2Resp("fareCalculation", null, orderContext);
        System.out.println("费用: " + orderContext.getFinalAmount() / 100L + " 元");
        return null;
    }
}
