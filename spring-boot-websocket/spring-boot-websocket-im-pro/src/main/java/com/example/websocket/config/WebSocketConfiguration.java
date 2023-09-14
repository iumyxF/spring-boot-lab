package com.example.websocket.config;

import com.example.websocket.server.WebSocketHandler;
import com.example.websocket.server.WebSocketShakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @description: WebSocket配置类
 * @Date 2023/2/28 14:18
 * @author iumyxF
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //添加websocket处理器
        registry.addHandler(webSocketHandler(), "/websocket/im")
                //添加websocket拦截器
                .addInterceptors(webSocketShakeInterceptor())
                //解决跨域问题
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler();
    }

    @Bean
    public WebSocketShakeInterceptor webSocketShakeInterceptor() {
        return new WebSocketShakeInterceptor();
    }
}
