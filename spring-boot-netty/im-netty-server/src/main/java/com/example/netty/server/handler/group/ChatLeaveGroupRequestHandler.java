package com.example.netty.server.handler.group;

import com.example.netty.common.dispatcher.MessageHandler;
import com.example.netty.server.message.chat.ChatSendResponse;
import com.example.netty.server.message.group.ChatLeaveGroupRequest;
import com.example.netty.server.service.NettyChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.ChannelMatchers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 退出组请求处理
 * @Date 2023/3/30 14:51
 * @author iumyxF
 */
@Component
public class ChatLeaveGroupRequestHandler implements MessageHandler<ChatLeaveGroupRequest> {

    @Resource
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatLeaveGroupRequest message) {
        ChannelGroup channelGroup = nettyChannelManager.getChannelGroup(message.getGroupName());
        //channelGroup.close(ChannelMatchers.is(channel)); //会将客户端channel直接断开连接
        channelGroup.remove(channel);

        //返回处理消息给sender
        ChatSendResponse response = new ChatSendResponse();
        response.setCode(200);
        response.setMessage("成功退出[" + message.getGroupName() + "]组");
    }

    @Override
    public String getType() {
        return ChatLeaveGroupRequest.TYPE;
    }
}
