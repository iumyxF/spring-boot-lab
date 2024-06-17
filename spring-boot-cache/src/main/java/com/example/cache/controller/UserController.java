package com.example.cache.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.cache.model.entities.User;
import com.example.cache.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2023/5/2 10:05
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Cacheable(value = "userList", sync = true)
    @GetMapping("/list")
    public List<User> list() {
        log.info("查询数据库...");
        return userService.list();
    }

    @Cacheable(value = "getUser", key = "#id", unless = "#result==null")
    @GetMapping("/{id}")
    public User info(@PathVariable Long id) {
        log.info("查询数据库...");
        return userService.getById(id);
    }

    @CacheEvict(value = "getUser", key = "#root.args[0]")
    @PostMapping("/{id}")
    public Boolean edit(@PathVariable String id) {
        log.info("操作数据库...");
        User user = userService.getById(id);
        user.setName(RandomUtil.randomString(3));
        return userService.updateById(user);
    }

    @CacheEvict(value = "getUser", key = "#root.args[0]")
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable String id) {
        log.info("操作数据库...");
        return userService.removeById(id);
    }

    @PutMapping("/")
    public Boolean add() {
        log.info("操作数据库");
        User user = new User();
        user.setAge(RandomUtil.randomInt(0, 100));
        user.setName(RandomUtil.randomString(3));
        return userService.save(user);
    }

}
