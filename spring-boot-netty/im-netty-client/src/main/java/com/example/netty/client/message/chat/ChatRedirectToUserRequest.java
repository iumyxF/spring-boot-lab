package com.example.netty.client.message.chat;

import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * 转发消息给一个用户的 Message
 *
 * @author iumyxF
 */
@Data
@ToString
public class ChatRedirectToUserRequest implements Message {

    public static final String TYPE = "CHAT_REDIRECT_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息来自谁
     */
    private String fromUser;
}
