package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户认证响应
 * @Date 2023/2/27 14:25
 * @author iumyxF
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应提示
     */
    private String message;
}
