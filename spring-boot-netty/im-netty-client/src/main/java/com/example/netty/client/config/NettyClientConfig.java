package com.example.netty.client.config;

import com.example.netty.common.dispatcher.MessageDispatcher;
import com.example.netty.common.dispatcher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @Date 2023/3/14 16:16
 * @author iumyxF
 */
@Configuration
public class NettyClientConfig {

    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }

}
