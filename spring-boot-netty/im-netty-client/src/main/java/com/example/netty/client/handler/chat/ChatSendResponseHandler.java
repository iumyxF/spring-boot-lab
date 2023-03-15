package com.example.netty.client.handler.chat;

import com.example.netty.client.message.chat.ChatSendResponse;
import com.example.netty.common.dispatcher.MessageHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 私聊消息响应处理器
 * @Date 2023/3/15 14:32
 * @Author fzy
 */
@Slf4j
@Component
public class ChatSendResponseHandler implements MessageHandler<ChatSendResponse> {
    @Override
    public void execute(Channel channel, ChatSendResponse message) {
        log.info("[execute][发送结果：{}]", message);
    }

    @Override
    public String getType() {
        return ChatSendResponse.TYPE;
    }
}
