package com.example.transaction.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.domain.Order;
import com.example.transaction.domain.User;
import com.example.transaction.mapper.OrderMapper;
import com.example.transaction.service.OrderService;
import com.example.transaction.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description 针对表【tr_order】的数据库操作Service实现
 * @createDate 2023-07-10 09:15:25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Order order) {
        User user = new User("张三");
        userService.add(user);

        System.out.println("add order before");
        orderMapper.insert(order);
        System.out.println("add order after");
        System.out.println("add order finish");
    }
}




