package com.example.transaction.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.domain.User;
import com.example.transaction.mapper.UserMapper;
import com.example.transaction.service.OrderService;
import com.example.transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description 针对表【tr_user】的数据库操作Service实现
 * @createDate 2023-07-10 09:15:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public void add(User user) {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(definition);
        try {
            System.out.println("add before");
            userMapper.insert(user);
            int i = 10 / 0;
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            System.out.println("add user 出现异常了");
            dataSourceTransactionManager.rollback(transactionStatus);
            throw new RuntimeException(e);
        }
        System.out.println("add after");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Long id) {
        User aaaaaa = new User("aaaaaa");
        userMapper.insert(aaaaaa);
        //int i = 10 / 0;
        return 0;
    }
}




