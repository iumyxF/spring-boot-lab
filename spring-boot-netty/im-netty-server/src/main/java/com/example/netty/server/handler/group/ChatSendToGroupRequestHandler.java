package com.example.netty.server.handler.group;


import com.example.netty.common.codec.Invocation;
import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.chat.ChatRedirectToUserRequest;
import com.example.netty.server.message.chat.ChatSendResponse;
import com.example.netty.server.message.group.ChatSendToGroupRequest;
import com.example.netty.server.service.NettyChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 群发消息处理（改良版）
 * @Date 2023/3/29 15:03
 * @author iumyxF
 */
@Component
public class ChatSendToGroupRequestHandler implements MessageHandler<ChatSendToGroupRequest> {

    @Resource
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToGroupRequest message) {
        //获取ChannelGroup
        ChannelGroup channelGroup = nettyChannelManager.getChannelGroup(message.getGroupName());
        if (null == channelGroup) {
            //返回消息发送结果
            ChatSendResponse response = new ChatSendResponse();
            response.setMessage("群聊消息发送失败，没有该组");
            response.setCode(500);
            response.setMsgId(message.getMsgId());
            channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));
            return;
        }
        //构造消息
        ChatRedirectToUserRequest send = new ChatRedirectToUserRequest();
        send.setContent(message.getContent());
        send.setMsgId(message.getMsgId());
        send.setFromUser(message.getFromUser());
        //发送消息
        channelGroup.writeAndFlush(new Invocation(ChatRedirectToUserRequest.TYPE, send));
        //返回消息发送结果
        ChatSendResponse response = new ChatSendResponse();
        response.setMessage("群聊消息发送成功");
        response.setCode(200);
        response.setMsgId(message.getMsgId());
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return ChatSendToGroupRequest.TYPE;
    }
}
