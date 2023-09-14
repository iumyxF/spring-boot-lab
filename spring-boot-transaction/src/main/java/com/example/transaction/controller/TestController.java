package com.example.transaction.controller;

import com.example.transaction.service.impl.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @date 2023/7/10 9:22
 */
@RestController
public class TestController {

    @Resource
    private TestServiceImpl testService;

    @GetMapping("/test")
    public void test() {
        testService.doServiceVersion3();
    }
}
