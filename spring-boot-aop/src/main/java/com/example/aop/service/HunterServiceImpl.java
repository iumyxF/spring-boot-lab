package com.example.aop.service;

import com.example.aop.annotation.HunterAsyncAnno;
import com.example.aop.annotation.HunterLogAnno;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @Date 2023/2/13 10:29
 * @Author fzy
 */
@Service
public class HunterServiceImpl {

    @HunterLogAnno(value = "mike")
    public void getTheLogs(String name) {
        System.out.println("service接受到的参数:" + name);
    }

    @HunterAsyncAnno
    public void asyncSystemOut(String name) {
        System.out.println("5秒后HunterServiceImpl执行方法...,参数name = " + name);
    }
}
