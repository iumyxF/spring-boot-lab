package com.example.workflow.controller;

import java.util.Date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.workflow.model.Order;
import com.example.workflow.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/7/29 13:40
 */
@RestController
public class TestController {

    @Resource
    private OrderService orderService;

    @GetMapping("/test/flow")
    public void test() {
        /*
         * 接单时间 2024-07-29 12:00:00 1722225600000
         *
         * 到达时间 2024-07-29 12:12:00 1722226320000
         *
         * 开始服务时间 2024-07-29 12:17:00 1722226620000
         *
         * 结束服务时间 2024-07-29 12:30:00 1722227400000
         *
         * 里程 8公里
         *
         * expect amount = 19+(3*3) = 28
         */
        Order order = new Order();
        order.setId(1001L);
        order.setAcceptTime(new DateTime(1722225600000L));
        order.setArriveTime(new DateTime(1722226320000L));
        order.setStartServiceTime(new DateTime(1722226620000L));
        order.setEndServiceTime(new DateTime(1722227400000L));
        order.setDistance(8L);

        orderService.calculateCost(order);
    }
}
