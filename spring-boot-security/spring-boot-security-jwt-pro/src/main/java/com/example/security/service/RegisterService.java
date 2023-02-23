package com.example.security.service;

import com.example.security.entities.RegisterBody;
import com.example.security.entities.User;
import com.example.security.mapper.UserMapper;
import com.example.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 注册服务
 * @Date 2023/2/23 14:05
 * @Author fzy
 */
@Service
public class RegisterService {

    @Resource
    private UserMapper userMapper;

    /**
     * 注册功能
     *
     * @param user 用户信息
     */
    public void register(RegisterBody user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User sysUser = new User();
        sysUser.setUsername(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        userMapper.insert(sysUser);
    }
}
