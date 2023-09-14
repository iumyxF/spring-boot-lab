package com.example.netty.client.handler.auth;

import com.example.netty.client.message.auth.AuthResponse;
import com.example.netty.common.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 认证响应处理
 * @Date 2023/3/15 10:26
 * @author iumyxF
 */
@Slf4j
@Component
public class AuthResponseHandler implements MessageHandler<AuthResponse> {
    @Override
    public void execute(Channel channel, AuthResponse message) {
        log.info("[execute][认证结果：{}]", message);
    }

    @Override
    public String getType() {
        return AuthResponse.TYPE;
    }
}
