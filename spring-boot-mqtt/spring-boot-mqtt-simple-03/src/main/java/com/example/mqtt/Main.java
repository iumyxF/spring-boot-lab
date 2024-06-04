package com.example.mqtt;

import com.example.mqtt.listener.DefaultMqttListener;
import com.example.mqtt.utils.MqttUtils;

/**
 * @author iumyxF
 * @description:
 * @date 2024/2/3 14:48
 */
public class Main {

    public static void main(String[] args) {
        MqttUtils.subscribe("venus/client/online", new DefaultMqttListener());
        //MqttUtils.send("/qaq/test_topic", "hello mqtt");
    }
}
