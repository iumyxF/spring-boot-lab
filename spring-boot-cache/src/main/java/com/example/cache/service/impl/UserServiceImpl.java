package com.example.cache.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cache.mapper.UserMapper;
import com.example.cache.service.UserService;
import com.example.cache.model.entities.User;
import org.springframework.stereotype.Service;

/**
 * @author iumyx
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-02 10:08:11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




