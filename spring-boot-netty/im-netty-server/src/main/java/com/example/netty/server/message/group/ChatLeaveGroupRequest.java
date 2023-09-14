package com.example.netty.server.message.group;

import com.example.netty.common.dispatcher.Message;
import lombok.Data;

/**
 * @description: 退出群组请求
 * @Date 2023/3/30 14:50
 * @author iumyxF
 */
@Data
public class ChatLeaveGroupRequest implements Message {

    public static final String TYPE = "CHAT_LEAVE_GROUP_REQUEST";

    /**
     * 离开的组名
     */
    private String groupName;
    
}
