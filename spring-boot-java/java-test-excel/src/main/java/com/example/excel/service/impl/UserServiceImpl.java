package com.example.excel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.excel.domain.User;
import com.example.excel.mapper.UserMapper;
import com.example.excel.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author iumyxF
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-09-07 10:41:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}