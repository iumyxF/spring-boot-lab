package com.example.dynamic.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dynamic.entities.User;
import com.example.dynamic.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 *
 * @description: user serivce
 * @Date 2023 /2/14 16:15
 * @author iumyxF
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> {

    private final UserMapper baseMapper;


    /**
     * Select user by id master user.
     *
     * @param id the id
     * @return the user
     */
    public User selectUserByIdMaster(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * Select user by id user.
     *
     * @param id the id
     * @return the user
     */
    @DS("slave")
    public User selectUserByIdSlave(Long id) {
        return baseMapper.selectById(id);
    }


}
