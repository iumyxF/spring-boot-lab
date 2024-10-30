package com.example.transaction.controller;

import com.example.transaction.service.impl.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/test0")
    public void test0() {
        testService.doServiceVersion0();
    }

    @GetMapping("/test")
    public void test() {
        testService.doServiceVersion3();
    }

    @GetMapping("/test2")
    public void test2() {
        testService.threadDoService();
    }

    @GetMapping("/test3")
    public void test3() {
        testService.threadDoServiceByException();
    }

    @GetMapping("/test4")
    public void test4(@RequestParam("error") Integer error) {
        testService.threadDoServiceSingleRollBack(error);
    }

    @GetMapping("/test1025")
    public void test1025() {
        testService.test1025();
    }
}
