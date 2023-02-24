package com.example.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.security.entities.Result;
import com.example.security.entities.User;
import com.example.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @Date 2023/2/23 14:16
 * @Author fzy
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @GetMapping("/user/list")
    public Result list() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        return Result.ok(users);
    }

}
