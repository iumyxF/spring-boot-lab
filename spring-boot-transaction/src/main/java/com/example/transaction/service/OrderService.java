package com.example.transaction.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.transaction.domain.Order;

/**
 * @author iumyxF
 * @description 针对表【tr_order】的数据库操作Service
 * @createDate 2023-07-10 09:15:25
 */
public interface OrderService extends IService<Order> {

    void add(Order order);
}
