package com.example.websocket.handler;

import com.example.websocket.entities.AuthRequest;
import com.example.websocket.entities.AuthResponse;
import com.example.websocket.entities.UserJoinNoticeRequest;
import com.example.websocket.utils.WebSocketUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;


/**
 * @description: 用户认证消息处理器
 * @Date 2023/2/27 14:42
 * @Author fzy
 */
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest> {
    @Override
    public void execute(WebSocketSession session, AuthRequest message) {
        if (!StringUtils.hasLength(message.getToken())) {
            WebSocketUtil.send(session, AuthResponse.TYPE, AuthResponse.builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .message("认证 token 为空")
                    .build());
            return;
        }
        // 添加到 WebSocketUtil 中.考虑到代码简化，我们先直接使用 accessToken 作为 User
        WebSocketUtil.addSession(session, message.getToken());

        // 判断是否认证成功。这里，假装直接成功
        WebSocketUtil.send(session, AuthResponse.TYPE, AuthResponse.builder()
                .code(HttpStatus.OK.value())
                .build());

        // 通知所有人，某个人加入了。更新在线人数列表
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE,
                UserJoinNoticeRequest.builder().nickname(message.getToken()).build());
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}

