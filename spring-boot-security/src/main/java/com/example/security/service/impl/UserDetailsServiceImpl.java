package com.example.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.security.entities.LoginUser;
import com.example.security.entities.User;
import com.example.security.mapper.UserMapper;
import com.example.security.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @Date 2023/2/17 10:41
 * @Author fzy
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        System.out.println("username是" + username);
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            // 异常信息会被ExceptionTranslationFilter捕获到
            throw new RuntimeException("用户不存在或密码错误");
        }
        // 授权
        List<String> permissions = new ArrayList<>(Arrays.asList("test", "admin"));

        System.out.println("loginUser是:" + new LoginUser(user, permissions));
        // 数据封装成UserDetails返回
        return new LoginUser(user, permissions);
    }
}
