package com.example.mqtt.factory;

import com.example.mqtt.config.MqttProperties;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author iumyxF
 * @description:
 * @date 2024/2/3 14:22
 */
public class MqttFactory {

    private static final Logger logger = LoggerFactory.getLogger(MqttFactory.class);

    private static final MqttProperties properties;

    private static MqttClient mqttClient;

    static {
        properties = new MqttProperties("tcp://localhost:1883");
    }

    public static MqttClient getMqttClient() {
        if (mqttClient == null) {
            init();
        }
        return mqttClient;
    }

    private static void init() {
        try {
            MqttClient client = new MqttClient(properties.getAddress(), "client-" + System.currentTimeMillis());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            if (!client.isConnected()) {
                client.connect(options);
            }
            mqttClient = client;
        } catch (MqttException e) {
            logger.error("mqtt client init error");
        }
    }
}
