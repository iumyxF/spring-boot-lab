package com.example.security.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.util.RandomUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 入门案例
 * @Date 2023/2/25 9:31
 * @Author fzy
 */
@RestController
@RequestMapping("/example")
public class ExampleController {

    @GetMapping("/login")
    public String doLogin(String username, String password) {
        String name = "zhang";
        String pwd = "123456";
        if (name.equals(username) && pwd.equals(password)) {
            StpUtil.login(10002);
            return "登录成功";
        }
        return "登录失败";
    }

    @RequestMapping("/isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    @GetMapping("/tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    @GetMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

}

