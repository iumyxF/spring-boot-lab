package com.example.provider.controller;

import com.example.provider.config.MqttProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author iumyxF
 * @description: 控制器测试发布消息
 * @date 2023/4/21 14:03
 */
@Controller
public class SendController {

    @Autowired
    private MqttProviderConfig providerClient;

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(int qos, boolean retained, String topic, String message) {
        try {
            providerClient.publish(qos, retained, topic, message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
