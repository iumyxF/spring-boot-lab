package com.example.aop.controller;

import com.example.aop.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Date 2023/2/13 11:04
 * @author iumyxF
 */
@RestController
public class LogTestController {

    @Log(title = "log测试控制层")
    @GetMapping("/log/test")
    public void test() {
        System.out.println("controller执行...");
    }
}
