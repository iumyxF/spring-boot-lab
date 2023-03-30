package com.example.netty.client.message.chat;

import com.example.netty.common.dispatcher.Message;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 加入到某个组中
 * @Date 2023/3/29 14:33
 * @Author fzy
 */
@Data
@ToString
public class ChatJoinGroupRequest implements Message {

    public static final String TYPE = "CHAT_JOIN_IN_GROUP_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 加入的组名
     */
    public String groupName;

    /**
     * 加入的用户
     */
    public String user;

}
