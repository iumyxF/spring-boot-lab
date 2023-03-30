package com.example.netty.server.handler.chat;

import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.chat.ChatJoinGroupRequest;
import com.example.netty.server.message.chat.ChatSendResponse;
import com.example.netty.server.service.NettyChannelManager;
import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 加入组请求消息处理器
 * @Date 2023/3/29 14:43
 * @Author fzy
 */
@Component
public class ChatJoinGroupRequestHandler implements MessageHandler<ChatJoinGroupRequest> {

    @Resource
    private NettyChannelManager nettyChannelManager;

    /**
     * 将该用户加入到某个组中
     *
     * @param channel 消息channel
     * @param message 消息实体
     */
    @Override
    public void execute(Channel channel, ChatJoinGroupRequest message) {
        //添加到组中 前面加一个用户是否存在的判断
        nettyChannelManager.addGroup(message.getGroupName(), channel);

        //返回处理消息给sender
        ChatSendResponse response = new ChatSendResponse();
        response.setMsgId(message.getMsgId());
        response.setCode(200);
        response.setMessage("成功加入[" + message.getGroupName() + "]组");
    }

    @Override
    public String getType() {
        return ChatJoinGroupRequest.TYPE;
    }
}
