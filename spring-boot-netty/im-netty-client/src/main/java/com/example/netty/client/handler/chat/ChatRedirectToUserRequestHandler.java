package com.example.netty.client.handler.chat;

import com.example.netty.client.message.chat.ChatRedirectToUserRequest;
import com.example.netty.common.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 接受服务器转发的消息处理器
 * @Date 2023/3/15 14:35
 * @Author fzy
 */
@Slf4j
@Component
public class ChatRedirectToUserRequestHandler implements MessageHandler<ChatRedirectToUserRequest> {

    @Override
    public void execute(Channel channel, ChatRedirectToUserRequest message) {
        log.info("[execute][收到消息：{}]", message);
    }

    @Override
    public String getType() {
        return ChatRedirectToUserRequest.TYPE;
    }
}
