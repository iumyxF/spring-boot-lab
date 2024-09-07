package com.example.excel.controller;

import com.alibaba.excel.EasyExcel;
import com.example.excel.domain.User;
import com.example.excel.listener.UserDataListener;
import com.example.excel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/9/7 16:06
 */
@Slf4j
@Controller
@RequestMapping
public class ImportTestController {

    @Resource(name = "threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private UserService userService;

    /**
     * 耗时：47850ms
     */
    @ResponseBody
    @GetMapping("/importExcel")
    public String importTest() {
        long startTime = System.currentTimeMillis();
        String fileName = "F:\\testData\\data1725696234754.xlsx";
        // 读取文件所有sheet
        EasyExcel.read(fileName, User.class, new UserDataListener(userService))
                .doReadAll();
        log.info("导入耗时/ms:" + (System.currentTimeMillis() - startTime));
        return "success";
    }
}
