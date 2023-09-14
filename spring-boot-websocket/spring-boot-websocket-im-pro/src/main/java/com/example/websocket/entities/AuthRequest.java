package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户认证请求
 * @Date 2023/2/27 14:22
 * @author iumyxF
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证令牌 token
     * 基于Token认证的WebSocket连接。https://developer.aliyun.com/article/229057
     */
    private String token;
}
