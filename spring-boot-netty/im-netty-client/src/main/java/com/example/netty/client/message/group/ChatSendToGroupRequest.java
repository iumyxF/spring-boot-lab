package com.example.netty.client.message.group;

import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 群发消息请求（改良版）
 * @Date 2023/3/29 14:57
 * @author iumyxF
 */
@Data
@ToString
public class ChatSendToGroupRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_GROUP_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 发送者
     */
    private String fromUser;

    /**
     * 内容
     */
    private String content;

}
