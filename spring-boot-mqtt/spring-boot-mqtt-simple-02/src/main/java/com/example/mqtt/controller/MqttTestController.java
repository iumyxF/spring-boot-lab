package com.example.mqtt.controller;

import com.example.mqtt.config.MqttGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @date 2023/10/21 10:54
 */
@RestController
@RequestMapping("/test")
public class MqttTestController {

    @Resource
    private MqttGateway mqttGateway;

    /**
     * sendData 消息
     * topic 订阅主题
     **/
    @RequestMapping("/sendMqtt1")
    public String sendMqtt(String sendData, String topic) {
        mqttGateway.sendToMqtt(topic, sendData);
        return "OK";
    }

    /**
     * sendData 消息
     * qos 消息级别 （对应QOS0、QOS1，QOS2）
     * topic 订阅主题
     **/
    @GetMapping("/sendMqtt2")
    public String sendMqtt(@RequestParam("sendData") String sendData,
                           @RequestParam("qos") int qos,
                           @RequestParam("topic") String topic) {
        mqttGateway.sendToMqtt(topic, qos, sendData);
        return "OK";
    }
}
