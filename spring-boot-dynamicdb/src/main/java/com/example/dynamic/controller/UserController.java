package com.example.dynamic.controller;

import com.example.dynamic.entities.User;
import com.example.dynamic.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: controller
 * @Date 2023/2/14 16:18
 * @author iumyxF
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/info")
    public void getUser(@RequestParam("id") Long id) throws JsonProcessingException {
        User master = userService.selectUserByIdMaster(id);
        String masterJson = objectMapper.writeValueAsString(master);
        log.info("master的数据user = " + masterJson);
        User slave = userService.selectUserByIdSlave(id);
        String slaveJson = objectMapper.writeValueAsString(slave);
        log.info("slave的数据user = " + slaveJson);
    }
}
