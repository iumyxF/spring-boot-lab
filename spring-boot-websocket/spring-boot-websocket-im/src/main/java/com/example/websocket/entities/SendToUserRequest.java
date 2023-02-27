package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 发送消息给一个用户的 Message
 * @Date 2023/2/27 14:33
 * @Author fzy
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendToUserRequest implements Message {

    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

}
