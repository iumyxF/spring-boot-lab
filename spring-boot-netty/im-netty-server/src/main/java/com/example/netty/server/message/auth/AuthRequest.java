package com.example.netty.server.message.auth;


import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * 用户认证请求
 * @author iumyxF
 */
@Data
@ToString
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;
}
