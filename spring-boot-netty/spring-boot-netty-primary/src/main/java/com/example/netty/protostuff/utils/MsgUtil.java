package com.example.netty.protostuff.utils;

import com.example.netty.protostuff.domain.MsgInfo;

/**
 * @description:
 * @Date 2023/3/10 15:49
 * @Author fzy
 */
public class MsgUtil {

    /**
     * 构建消息实体
     *
     * @param channelId  用channelID作为实体ID
     * @param msgContent 消息内容
     * @return MsgInfo消息实体
     */
    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId, msgContent);
    }

}
