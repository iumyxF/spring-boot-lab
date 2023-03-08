package com.example.sign.controller;

import com.example.sign.annotation.CheckSignature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 随机名字接口
 * @Date 2023/3/8 10:40
 * @Author fzy
 */
@RestController
@RequestMapping("/name")
public class NameController {
    /**
     * 提供一个随机名称的接口
     */
    @CheckSignature
    @GetMapping("/")
    public String randomName() {
        return "张三";
    }

}
