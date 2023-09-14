package com.example.security.service;

import com.example.security.entities.LoginUser;
import com.example.security.entities.User;
import com.example.security.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @description: 用户验证处理
 * @Date 2023/2/22 10:12
 * @author iumyxF
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordService passwordService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUserName(username);
        if (Objects.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new RuntimeException("登录用户：" + username + " 不存在");
        }
        passwordService.validate(user);
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user) {
        return new LoginUser(user.getUserId(), user);
    }
}
