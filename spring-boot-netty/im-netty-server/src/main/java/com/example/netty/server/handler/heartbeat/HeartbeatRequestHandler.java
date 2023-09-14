package com.example.netty.server.handler.heartbeat;

import com.example.netty.common.codec.Invocation;
import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.heartbeat.HeartbeatRequest;
import com.example.netty.server.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 心跳请求处理器
 * @Date 2023/3/15 10:12
 * @author iumyxF
 */
@Slf4j
@Component
public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {
    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        log.info("[execute][收到连接({}) 的心跳请求]", channel.id());
        // 响应心跳
        HeartbeatResponse response = new HeartbeatResponse();
        channel.writeAndFlush(new Invocation(HeartbeatResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return HeartbeatRequest.TYPE;
    }
}
