package com.example.netty.client.message.chat;

import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * 发送给指定人的私聊消息 Request
 *
 * @author iumyxF
 */
@Data
@ToString
public class ChatSendToOneRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ONE_REQUEST";

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
