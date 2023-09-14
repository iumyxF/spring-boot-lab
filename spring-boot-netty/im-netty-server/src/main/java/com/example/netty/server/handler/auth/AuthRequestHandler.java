package com.example.netty.server.handler.auth;

import com.example.netty.common.codec.Invocation;
import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.auth.AuthRequest;
import com.example.netty.server.message.auth.AuthResponse;
import com.example.netty.server.service.NettyChannelManager;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @description: 认证请求处理器
 * @Date 2023/3/14 15:28
 * @author iumyxF
 */
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Resource
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, AuthRequest authRequest) {
        // 如果未传递 accessToken
        if (StringUtils.isEmpty(authRequest.getAccessToken())) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setCode(1);
            authResponse.setMessage("认证 accessToken 未传入");
            channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
            return;
        }

        // ... 此处应有一段

        // 将用户和 Channel 绑定
        // 考虑到代码简化，我们先直接使用 accessToken 作为 User
        nettyChannelManager.addUser(channel, authRequest.getAccessToken());

        // 响应认证成功
        AuthResponse authResponse = new AuthResponse();
        authResponse.setCode(200);
        authResponse.setMessage("认证成功");
        channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
