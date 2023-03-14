package com.example.netty.client.message.chat;


import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * 聊天发送消息结果的 Response
 *
 * @author iumyxF
 */
@Data
@ToString
public class ChatSendResponse implements Message {

    public static final String TYPE = "CHAT_SEND_RESPONSE";

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
