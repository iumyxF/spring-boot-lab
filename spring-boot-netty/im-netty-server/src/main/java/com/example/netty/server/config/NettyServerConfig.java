package com.example.netty.server.config;

import com.example.netty.common.dispatcher.MessageDispatcher;
import com.example.netty.common.dispatcher.MessageHandlerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 服务端配置文件
 * @Date 2023/3/14 10:47
 * @Author fzy
 */
@Configuration
public class NettyServerConfig {
    @Bean
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Bean
    public MessageHandlerContainer messageHandlerContainer() {
        return new MessageHandlerContainer();
    }

}
