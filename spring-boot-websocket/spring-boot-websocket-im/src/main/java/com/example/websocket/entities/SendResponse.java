package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 发送消息响应结果的 Message
 * @Date 2023/2/27 14:32
 * @Author fzy
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendResponse implements Message {

    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;
}
