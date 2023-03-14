package com.example.netty.server.message.heartbeat;


import com.example.netty.common.dispatcher.Message;

/**
 * 消息 - 心跳响应
 *
 * @author iumyxF
 */
public class HeartbeatResponse implements Message {

    /**
     * 类型 - 心跳响应
     */
    public static final String TYPE = "HEARTBEAT_RESPONSE";

    @Override
    public String toString() {
        return "HeartbeatResponse{}";
    }

}
