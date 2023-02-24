package com.example.security.controller;

import com.example.security.entities.LoginBody;
import com.example.security.entities.Result;
import com.example.security.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 登录
 * @Date 2023/2/23 10:22
 * @Author fzy
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        return Result.ok("token", token);
    }

}
