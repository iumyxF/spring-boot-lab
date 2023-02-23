package com.example.security.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.security.annotation.Anonymous;
import com.example.security.entities.LoginUser;
import com.example.security.entities.Result;
import com.example.security.entities.User;
import com.example.security.mapper.UserMapper;
import com.example.security.utils.RedisCache;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private final RedisCache redisCache;

    @GetMapping("/user/list")
    public Result list() {
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        return Result.ok(users);
    }

    @GetMapping("/test/put1")
    public Result put1() {
        User user = userMapper.selectById(1);
        redisCache.setCacheObject("test", user);
        User test = redisCache.getCacheObject("test");
        return Result.ok(test);
    }

    @GetMapping("/test/put2")
    public Result put2() {
        User user = userMapper.selectById(1);
        LoginUser loginUser = new LoginUser(user.getUserId(), user);
        redisCache.setCacheObject("hello", loginUser);
        String value = redisCache.getCacheObject("hello");
        return Result.ok(value);
    }


}
