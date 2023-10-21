package com.example.mqtt.config;

import com.example.mqtt.handler.MqttReceiveHandle;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author iumyxF
 * @description:
 * @date 2023/10/21 10:37
 */
@Slf4j
@Configuration
@IntegrationComponentScan
public class MqttSenderAndReceiveConfig {

    private static final byte[] WILL_DATA;

    static {
        WILL_DATA = "offline".getBytes(StandardCharsets.UTF_8);
    }

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    /**
     * 自定义的topic
     **/
    @Value("${spring.mqtt.default.myGo}")
    private String soyorin;

    /**
     * 连接超时
     **/
    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout;

    @Resource
    private MqttReceiveHandle mqttReceiveHandle;

    /**
     * MQTT连接器选项
     **/
    @Bean(value = "getMqttConnectOptions")
    public MqttConnectOptions getMqttConnectOptions1() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        mqttConnectOptions.setCleanSession(true);
        // 设置超时时间 单位为秒
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线，但这个方法并没有重连的机制
        mqttConnectOptions.setKeepAliveInterval(10);
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
        mqttConnectOptions.setWill("willTopic", WILL_DATA, 2, false);
        return mqttConnectOptions;
    }

    /**
     * MQTT工厂
     **/
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions1());
        return factory;
    }

    /**
     * MQTT信息通道（生产者）
     **/
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（生产者）
     **/
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        return messageHandler;
    }

    /**
     * 配置client,监听的topic
     * MQTT消息订阅绑定（消费者）
     **/
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId + "_inbound1", mqttClientFactory(),
                        defaultTopic, soyorin);
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * MQTT信息通道（消费者）
     **/
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    /**
     * MQTT消息处理器（消费者）
     **/
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            //处理接收消息
            mqttReceiveHandle.handle(message);
        };
    }

}
