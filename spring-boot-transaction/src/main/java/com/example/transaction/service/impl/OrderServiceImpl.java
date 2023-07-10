package com.example.transaction.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.domain.Order;
import com.example.transaction.mapper.OrderMapper;
import com.example.transaction.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author iumyxF
 * @description 针对表【tr_order】的数据库操作Service实现
 * @createDate 2023-07-10 09:15:25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}




