package com.example.aop.controller;

import com.example.aop.annotation.Log;
import com.example.aop.entities.OperationLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author iumyxF
 * @description:
 * @Date 2023/2/13 11:04
 */
@RestController
public class LogTestController {

    @Log(title = "log测试控制层")
    @GetMapping("/log/test")
    public void test() {
        System.out.println("controller执行...");
    }

    @Log(title = "test2")
    @GetMapping("/log/test2")
    public void test2(OperationLog log) {
        System.out.println(log.toString());
    }
}
