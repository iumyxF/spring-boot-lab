package com.example.netty.client.message.auth;

import com.example.netty.common.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * 用户认证响应
 * @author iumyxF
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
