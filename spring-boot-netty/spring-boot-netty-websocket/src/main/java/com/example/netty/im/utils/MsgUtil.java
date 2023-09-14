package com.example.netty.im.utils;

import com.alibaba.fastjson.JSON;
import com.example.netty.im.domain.ServerMsgProtocol;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @description:
 * @Date 2023/3/13 15:45
 * @author iumyxF
 */
public class MsgUtil {

    /**
     * 创建群聊消息
     */
    public static TextWebSocketFrame buildMsgAll(String channelId, String msgInfo) {
        //模拟头像
        int i = Math.abs(channelId.hashCode()) % 10;

        ServerMsgProtocol msg = new ServerMsgProtocol();
        //链接信息;1自发信息、2群发消息
        msg.setType(2);
        msg.setChannelId(channelId);
        msg.setUserHeadImg("head" + i + ".jpg");
        msg.setMsgInfo(msgInfo);

        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }

    /**
     * 创建私聊消息
     */
    public static TextWebSocketFrame buildMsgOwner(String channelId) {
        ServerMsgProtocol msg = new ServerMsgProtocol();
        //链接信息;1链接信息、2消息信息
        msg.setType(1);
        msg.setChannelId(channelId);
        return new TextWebSocketFrame(JSON.toJSONString(msg));
    }
}
