package com.example.transaction.service.impl;


import com.example.transaction.TransactionApplication;
import com.example.transaction.domain.Order;
import com.example.transaction.service.OrderService;
import com.example.transaction.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @date 2023/11/29 14:13
 */
@SpringBootTest(classes = TransactionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestServiceImplTest {

    @Resource
    TestServiceImpl testService;
    @Resource
    OrderService orderService;
    @Resource
    UserService userService;

    @Test
    public void addOrderTest() {
        Order order = new Order();
        order.setPrice(9999);
        orderService.add(order);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme