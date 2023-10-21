package com.example.mqtt.controller;

import com.example.mqtt.config.MqttGateway;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author iumyxF
 * @description:
 * @date 2023/10/21 11:34
 */
@RestController
@RequestMapping
public class DynamicSubTestController {

    @Resource
    private MqttGateway mqttGateway;

    @Resource
    private MessageProducer messageProducer;

    @Resource
    private MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter;

    /**
     * 方式1
     */
    @GetMapping("/addTopic")
    public String addTopic(@RequestParam("topicName") String topicName) {
        ((MqttPahoMessageDrivenChannelAdapter) messageProducer).addTopic(topicName);
        return "ok";
    }

    /**
     * 方式2
     */
    @GetMapping("/addTopic2")
    public String addTopic2(@RequestParam("topicName") String topicName) {
        mqttPahoMessageDrivenChannelAdapter.addTopic(topicName);
        return "ok";
    }


}
