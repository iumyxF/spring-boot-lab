package com.example.media.handler.binary;

import com.example.media.cache.MediaCacheHandler;
import com.example.media.common.bo.MediaBinaryData;
import com.example.media.common.enums.BinaryMessageType;
import com.example.media.manager.NettyChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author feng
 * @description:
 * @date 2024/11/13 21:03
 */
@Component
public class H264Handler implements BinaryMessageHandler {

    @Resource
    private NettyChannelManager manager;
    @Resource(name = "mediaThreadPoolTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private MediaCacheHandler cacheHandler;

    @Override
    public int getHandlerType() {
        return BinaryMessageType.H264.getValue();
    }

    @Override
    public void handle(Channel channel, MediaBinaryData data) {
        ChannelGroup channelGroup = manager.getChannelGroup(channel);
        if (null == channelGroup) {
            return;
        }
        // 不需要发送给自己的channel
        channelGroup.writeAndFlush(data, c -> !Objects.equals(c.id(), channel.id()));
        // 缓存数据
        taskExecutor.execute(() -> cacheHandler.put(channelGroup.name(), data));
    }
}
