package com.example.aop.controller;

import com.example.aop.annotation.Log;
import com.example.aop.entities.OperationLog;
import org.springframework.web.bind.annotation.*;

/**
 * @author iumyxF
 * @description:
 * @Date 2023/2/13 11:04
 */
@RestController
public class LogTestController {

    @Log(title = "log测试控制层", content = "#{#name}", content2 = "#{#age}")
    @GetMapping("/log/test")
    public void test(@RequestParam("name") String name,
                     @RequestParam("age") Integer age) {
        System.out.println("controller执行...");
    }

    @Log(title = "test2", content = "#{#log.method}")
    @GetMapping("/log/test2")
    public void test2(OperationLog log) {
        System.out.println(log.toString());
    }

    @Log(title = "xxxx", content = "#{#log.method}")
    @PostMapping("/log/test3")
    public void test(@RequestBody OperationLog log) {
        System.out.println(log.toString());
        System.out.println("controller执行...");
    }
}
