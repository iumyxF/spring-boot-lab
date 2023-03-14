package com.example.netty.client.message.heartbeat;


import com.example.netty.common.dispatcher.Message;

/**
 * 消息 - 心跳请求
 *
 * @author iumyxF
 */
public class HeartbeatRequest implements Message {

    /**
     * 类型 - 心跳请求
     */
    public static final String TYPE = "HEARTBEAT_REQUEST";

    @Override
    public String toString() {
        return "HeartbeatRequest{}";
    }

}
