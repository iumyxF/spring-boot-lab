package com.example.plus.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.plus.mapper.UserMapper;
import com.example.plus.model.entities.User;
import com.example.plus.model.vo.UserVo;
import com.example.plus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 *
 * @author iumyx
 * @description 针对表 【user】的数据库操作Service实现
 * @createDate 2023 -05-09 13:55:28
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserVo selectUserVoById(Long userId) {
        return userMapper.selectUserVoById(userId);
    }
}




