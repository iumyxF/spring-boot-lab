package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Date 2023/2/17 11:02
 * @author iumyxF
 */
@RestController
@RequestMapping
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

}
