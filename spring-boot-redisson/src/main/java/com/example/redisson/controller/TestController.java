package com.example.redisson.controller;

import com.example.redisson.utils.RedisUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author iumyxF
 * @description: 测试
 * @date 2023/5/8 10:01
 */
@RestController
public class TestController {

    @GetMapping("/get")
    public String getCache() {
        return RedisUtils.getCacheObject("test");
    }

    @PutMapping("/add")
    public void addCache() {
        RedisUtils.setCacheObject("test", "hello");
    }

    @DeleteMapping("/remove")
    public void removeCache() {
        RedisUtils.deleteObject("test");
    }
}
