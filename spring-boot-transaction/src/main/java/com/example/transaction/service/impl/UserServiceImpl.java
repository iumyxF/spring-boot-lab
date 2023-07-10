package com.example.transaction.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.transaction.domain.User;
import com.example.transaction.mapper.UserMapper;
import com.example.transaction.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author iumyxF
 * @description 针对表【tr_user】的数据库操作Service实现
 * @createDate 2023-07-10 09:15:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




