package com.example.aop.controller;

import com.example.aop.service.HunterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @Date 2023/2/13 10:30
 * @author iumyxF
 */
@RestController
@RequiredArgsConstructor
public class HunterController {

    private final HunterServiceImpl hunterService;

    @RequestMapping("/hunter")
    @ResponseBody
    public String reqHunterAnno() {
        hunterService.getTheLogs("honda");
        return "ok";
    }

    @RequestMapping("/hunterAsync")
    @ResponseBody
    public String reqHunterAsyncAnno() {
        System.out.println("请求开始时间: " + System.currentTimeMillis());
        hunterService.asyncSystemOut("Integra!!");
        return "ok";
    }

}
