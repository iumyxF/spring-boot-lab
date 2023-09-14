package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 发送消息相关 Message
 * @Date 2023/2/27 14:30
 * @author iumyxF
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendToOneRequest implements Message {

    public static final String TYPE = "SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;
    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

}
