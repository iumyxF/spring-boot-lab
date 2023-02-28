package com.example.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户成功认证之后，会广播用户加入群聊的通知 Message
 * @Date 2023/2/27 14:25
 * @Author fzy
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinNoticeRequest implements Message{

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;

}
