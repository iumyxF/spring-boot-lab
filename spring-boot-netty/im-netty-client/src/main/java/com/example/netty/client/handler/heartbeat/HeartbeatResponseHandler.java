package com.example.netty.client.handler.heartbeat;

import com.example.netty.client.message.heartbeat.HeartbeatResponse;
import com.example.netty.common.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 心跳响应处理器
 * @Date 2023/3/15 10:14
 * @author iumyxF
 */
@Slf4j
@Component
public class HeartbeatResponseHandler implements MessageHandler<HeartbeatResponse> {
    @Override
    public void execute(Channel channel, HeartbeatResponse message) {
        log.info("[execute][收到连接({}) 的心跳响应]", channel.id());
    }

    @Override
    public String getType() {
        return HeartbeatResponse.TYPE;
    }
}
