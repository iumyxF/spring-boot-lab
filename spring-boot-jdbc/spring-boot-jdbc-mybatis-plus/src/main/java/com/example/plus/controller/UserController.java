package com.example.plus.controller;

import com.example.plus.mapper.UserMapper;
import com.example.plus.model.Result;
import com.example.plus.model.entities.User;
import com.example.plus.model.vo.UserVo;
import com.example.plus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/9 14:30
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/user/{id}")
    public Result<UserVo> info(@PathVariable Long id) {
        UserVo userVo = userService.selectUserVoById(id);
        return Result.ok(userVo);
    }

    @GetMapping("/user")
    public Result<List<UserVo>> list() {

        return Result.ok();
    }

    @PutMapping("/user")
    public Result<User> insert() {
        User user = new User();
        user.setUserName("foj");
        user.setAge(213);
        userMapper.insert(user);
        return Result.ok(user);
    }
}
