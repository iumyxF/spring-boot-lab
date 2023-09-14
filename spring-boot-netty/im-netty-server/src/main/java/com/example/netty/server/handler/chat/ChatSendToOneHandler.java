package com.example.netty.server.handler.chat;

import com.example.netty.common.codec.Invocation;
import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.chat.ChatRedirectToUserRequest;
import com.example.netty.server.message.chat.ChatSendResponse;
import com.example.netty.server.message.chat.ChatSendToOneRequest;
import com.example.netty.server.service.NettyChannelManager;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 私聊消息请求处理器
 * @Date 2023/3/15 14:19
 * @author iumyxF
 */
@Slf4j
@Component
public class ChatSendToOneHandler implements MessageHandler<ChatSendToOneRequest> {

    @Resource
    private NettyChannelManager nettyChannelManager;

    /**
     * 转发 响应
     *
     * @param channel 消息channel
     * @param message 消息实体
     */
    @Override
    public void execute(Channel channel, ChatSendToOneRequest message) {
        //转发接收端
        ChatRedirectToUserRequest toUserRequest = new ChatRedirectToUserRequest();
        toUserRequest.setMsgId(message.getMsgId());
        toUserRequest.setContent(message.getContent());
        nettyChannelManager.send(message.getToUser(), new Invocation(ChatRedirectToUserRequest.TYPE, toUserRequest));

        //响应发送端
        ChatSendResponse response = new ChatSendResponse();
        response.setCode(200);
        response.setMessage("私聊消息 " + message.getMsgId() + " ,发送成功");
        response.setMsgId(message.getMsgId());
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return ChatSendToOneRequest.TYPE;
    }
}
