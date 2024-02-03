package com.example.mqtt.utils;

import com.example.mqtt.factory.MqttFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author iumyxF
 * @description:
 * @date 2024/2/3 14:27
 */
public class MqttUtils {

    private static final Logger logger = LoggerFactory.getLogger(MqttUtils.class);

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param data  内容
     */
    public static void send(String topic, Object data) {
        MqttClient client = MqttFactory.getMqttClient();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonData = mapper.writeValueAsString(data);
            client.publish(topic, new MqttMessage(jsonData.getBytes(StandardCharsets.UTF_8)));
        } catch (JsonProcessingException e) {
            logger.error("JSON conversion failed");
        } catch (MqttException e) {
            logger.error("mqtt send message error , topic = {}", topic);
        }
    }

    /**
     * 订阅某个主题
     *
     * @param topic    主题
     * @param listener 监听器
     */
    public static void subscribe(String topic, IMqttMessageListener listener) {
        MqttClient client = MqttFactory.getMqttClient();
        try {
            client.subscribe(topic, listener);
        } catch (MqttException e) {
            logger.error("mqtt subscribe fail , topic = {}", topic);
        }
    }
}
