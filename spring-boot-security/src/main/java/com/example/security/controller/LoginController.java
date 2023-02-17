package com.example.security.controller;

import com.example.security.entities.Result;
import com.example.security.entities.User;
import com.example.security.service.ILoginService;
import com.example.security.service.ILogoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Date 2023/2/17 11:06
 * @Author fzy
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private ILogoutService logoutService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user) {
        log.info("访问了登录接口start");
        log.info("前端接收的User:" + user);

        //登录
        Result login = loginService.login(user);
        log.info("访问了登录接口end");
        return login;
    }

    @GetMapping("/user/logout")
    public Result logout() {
        log.info("访问了注销接口start");
        Result result = logoutService.logout();
        log.info("访问了注销接口end");
        return result;
    }
}
