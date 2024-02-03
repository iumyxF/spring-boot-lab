package com.example.mqtt.listener;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iumyxF
 * @description: mqtt 接收处理器
 * @date 2024/2/3 14:38
 */
public class DefaultMqttListener implements IMqttMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMqttListener.class);

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        logger.info("arrived mqtt , topic = {} , message = {}", topic, message.toString());
        //service ...
    }
}
