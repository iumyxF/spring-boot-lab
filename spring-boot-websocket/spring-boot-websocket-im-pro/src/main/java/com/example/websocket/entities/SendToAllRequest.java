package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 发送给所有人的群聊消息的 Message
 * @Date 2023/2/27 14:31
 * @author iumyxF
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
}
