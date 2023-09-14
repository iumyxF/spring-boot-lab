package com.example.security.controller;

import com.example.security.entities.Result;
import com.example.security.entities.User;
import com.example.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Date 2023/2/17 11:15
 * @author iumyxF
 */
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int insert = userMapper.insert(user);
        return Result.ok("注册成功", insert);
    }

}
