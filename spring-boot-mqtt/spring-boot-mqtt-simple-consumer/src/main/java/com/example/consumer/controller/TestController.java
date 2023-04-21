package com.example.consumer.controller;

import com.example.consumer.config.MqttConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fzy
 * @description:
 * @date 2023/4/21 14:10
 */
@Controller
public class TestController {

    @Autowired
    private MqttConsumerConfig client;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @RequestMapping("connect")
    @ResponseBody
    public String connect() {
        client.connect();
        return clientId + "连接到服务器";
    }

    @RequestMapping("disConnect")
    @ResponseBody
    public String disConnect() {
        client.disConnect();
        return clientId + "与服务器断开连接";
    }
}
