package com.example.media.manager;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 20:57
 */
@Component
public class NettyChannelManager {

    private static final AttributeKey<String> CHANNEL_ATTR_KEY_GROUP_NAME = AttributeKey.newInstance("groupName");

    private static final Map<String, ChannelGroup> CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    public void register(Channel channel, String groupName) {
        ChannelGroup channelGroup = CHANNEL_GROUP_MAP.computeIfAbsent(groupName,
                c -> new DefaultChannelGroup(groupName, GlobalEventExecutor.INSTANCE));
        channelGroup.add(channel);
        channel.attr(CHANNEL_ATTR_KEY_GROUP_NAME).set(groupName);
    }

    public void unregister(Channel channel) {
        String groupName = channel.attr(CHANNEL_ATTR_KEY_GROUP_NAME).get();
        if (null == groupName) {
            return;
        }
        ChannelGroup channelGroup = CHANNEL_GROUP_MAP.get(groupName);
        if (null == channelGroup) {
            return;
        }
        channelGroup.close(c -> Objects.equals(channel.id(), c.id()));
        if (channelGroup.isEmpty()) {
            CHANNEL_GROUP_MAP.remove(groupName);
        }
    }

    public ChannelGroup getChannelGroup(Channel channel) {
        String groupName = channel.attr(CHANNEL_ATTR_KEY_GROUP_NAME).get();
        if (null == groupName) {
            return null;
        }
        return CHANNEL_GROUP_MAP.get(groupName);
    }

}
