package com.example.netty.server.service;

import com.example.netty.common.codec.Invocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description: 服务端对客户端Channel的管理 具体实现
 * @Date 2023/3/14 14:43
 * @Author fzy
 */
@Slf4j
@Component
public class NettyChannelManager {

    /**
     * {@link Channel#attr(AttributeKey)} 属性中，表示 Channel 对应的用户
     */
    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER = AttributeKey.newInstance("user");

    /**
     * Channel 映射
     */
    private ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();

    /**
     * 用户与 Channel 的映射。
     * 通过它，可以获取用户对应的 Channel。这样，我们可以向指定用户发送消息。
     */
    private ConcurrentMap<String, Channel> userChannels = new ConcurrentHashMap<>();

    /**
     * 群组
     */
    private static Map<String, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

    /**
     * 添加 Channel 到 {@link #channels} 中
     *
     * @param channel Channel
     */
    public void add(Channel channel) {
        channels.put(channel.id(), channel);
        log.info("[add][一个连接({})加入]", channel.id());
    }

    /**
     * 添加指定用户到 {@link #userChannels} 中
     *
     * @param channel Channel
     * @param user    用户
     */
    public void addUser(Channel channel, String user) {
        Channel existChannel = channels.get(channel.id());
        if (existChannel == null) {
            log.error("[addUser][连接({}) 不存在]", channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 添加到 userChannels
        userChannels.put(user, channel);
    }

    /**
     * 将 Channel 从 {@link #channels} 和 {@link #userChannels} 中移除
     *
     * @param channel Channel
     */
    public void remove(Channel channel) {
        // 移除 channels
        channels.remove(channel.id());
        // 移除 userChannels, 判断该channel中是否存在CHANNEL_ATTR_KEY_USER属性
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)) {
            userChannels.remove(channel.attr(CHANNEL_ATTR_KEY_USER).get());
        }
        log.info("[remove][一个连接({})离开]", channel.id());
    }

    /**
     * 向指定用户发送消息
     *
     * @param user       用户
     * @param invocation 消息体
     */
    public void send(String user, Invocation invocation) {
        // 获得用户对应的 Channel
        Channel channel = userChannels.get(user);
        if (channel == null) {
            log.error("[send][连接不存在]");
            return;
        }
        if (!channel.isActive()) {
            log.error("[send][连接({})未激活]", channel.id());
            return;
        }
        // 发送消息
        channel.writeAndFlush(invocation);
    }

    /**
     * 向所有用户发送消息
     *
     * @param invocation 消息体
     * @param id         发送端ID
     */
    public void sendAll(Invocation invocation, ChannelId id) {
        Channel selfChannel = channels.get(id);
        if (selfChannel == null) {
            log.error("当前channel不存在，或断开连接");
        }
        for (Channel channel : channels.values()) {
            //排除自己不用发送
            if (channel.equals(selfChannel)) {
                continue;
            }
            if (!channel.isActive()) {
                log.error("[send][连接({})未激活]", channel.id());
                return;
            }
            // 发送消息
            channel.writeAndFlush(invocation);
        }
    }

    /**
     * 将某个channel加入到组
     *
     * @param groupName
     * @param channel
     */
    public synchronized void addGroup(String groupName, Channel channel) {
        //获取该组名的ChannelGroup
        ChannelGroup channelGroup = channelGroupMap.get(groupName);
        if (null == channelGroup) {
            channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            channelGroupMap.put(groupName, channelGroup);
        }
        //将用户channel添加到channelGroup
        channelGroup.add(channel);
    }

    /**
     * 根据组名获取ChannelGroup
     *
     * @param groupName 组名
     * @return ChannelGroup
     */
    public ChannelGroup getChannelGroup(String groupName) {
        return channelGroupMap.get(groupName);
    }
}
