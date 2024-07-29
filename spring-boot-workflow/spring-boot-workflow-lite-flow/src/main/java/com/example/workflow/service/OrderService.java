package com.example.workflow.service;

import com.example.workflow.model.Order;

/**
 * @author fzy
 * @description:
 * @date 2024/7/27 14:13
 */
public interface OrderService {

    /**
     * 计算订单费用
     *
     * @param order 订单信息
     * @return 金额 单位分
     */
    Long calculateCost(Order order);
}
