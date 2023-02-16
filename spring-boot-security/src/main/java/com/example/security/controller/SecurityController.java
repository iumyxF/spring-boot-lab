package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Date 2023/2/16 10:06
 * @Author fzy
 */
@RestController
public class SecurityController {

    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "index";
    }
}
