package com.example.netty.server.handler.chat;

import com.example.netty.common.codec.Invocation;
import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.chat.ChatRedirectToUserRequest;
import com.example.netty.server.message.chat.ChatSendResponse;
import com.example.netty.server.message.chat.ChatSendToAllRequest;
import com.example.netty.server.service.NettyChannelManager;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 群发消息接受处理器
 * @Date 2023/3/15 15:04
 * @author iumyxF
 */
@Component
public class ChatSendToAllHandler implements MessageHandler<ChatSendToAllRequest> {

    @Resource
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToAllRequest message) {
        //转发
        ChatRedirectToUserRequest toUserRequest = new ChatRedirectToUserRequest();
        toUserRequest.setMsgId(message.getMsgId());
        toUserRequest.setContent(message.getContent());
        toUserRequest.setFromUser(channel.id().toString());
        nettyChannelManager.sendAll(new Invocation(ChatRedirectToUserRequest.TYPE, toUserRequest), channel.id());

        //响应
        ChatSendResponse response = new ChatSendResponse();
        response.setMsgId(message.getMsgId());
        response.setCode(200);
        response.setMessage("群发消息发送成功");
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return ChatSendToAllRequest.TYPE;
    }
}
