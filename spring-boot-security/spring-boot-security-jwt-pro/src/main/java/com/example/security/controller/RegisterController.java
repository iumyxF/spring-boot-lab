package com.example.security.controller;

import com.example.security.entities.RegisterBody;
import com.example.security.entities.Result;
import com.example.security.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Date 2023/2/23 14:07
 * @author iumyxF
 */
@Validated
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterBody user) {
        registerService.register(user);
        return Result.ok("注册成功");
    }

}

