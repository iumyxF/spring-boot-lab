package com.example.mqtt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author iumyxF
 * @description:
 * @date 2023/10/21 10:49
 */
@Slf4j
@Component
public class MqttReceiveHandle {

    public void handle(Message<?> message) {
        log.info("主题：{}，QOS:{}，消息接收到的数据：{}", message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC), message.getHeaders().get(MqttHeaders.RECEIVED_QOS), message.getPayload());
    }
}
