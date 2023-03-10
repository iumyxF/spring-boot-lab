package com.example.consumer;

import com.example.sms.service.SmsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author feng
 * @date 2023/3/9 20:39
 */
@RestController
@RequestMapping("/hello")
public class ConsumerController {
    /**
     * 自定义的starter中已经完成了自动配置，所以此处可以直接注入
     */
    @Resource
    private SmsService smsService;

    @GetMapping("/sms")
    public String sendSms() {
        smsService.send("18888888888", "mySignName", "666", "hello starter");
        return "send success";
    }
}
