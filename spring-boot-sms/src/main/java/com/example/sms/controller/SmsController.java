package com.example.sms.controller;

import com.example.sms.entities.SmsResult;
import com.example.sms.service.SmsTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description: 短信测试
 * @Date 2023/2/15 10:38
 * @author iumyxF
 */
@Slf4j
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private SmsTemplate smsTemplate;
    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/send")
    public void send(@RequestParam("phones") String phones,
                     @RequestParam("templateId") String templateId) throws JsonProcessingException {
        HashMap<String, String> params = new HashMap<>(1);
        params.put("code", "123456");
        SmsResult result = smsTemplate.send(phones, templateId, params);
        log.info(objectMapper.writeValueAsString(result));
    }

}
