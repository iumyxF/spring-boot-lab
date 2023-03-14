package com.example.netty.client.message.chat;


import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * 发送给所有人的群聊消息的 Message
 *
 * @author iumyxF
 */
@Data
@ToString
public class ChatSendToAllRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
}
