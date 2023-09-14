package com.example.netty.im.utils;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @description: 存储用户channel
 * @Date 2023/3/13 14:47
 * @author iumyxF
 */
public class MyChannelHandler {

    /**
     * 存储每一个客户端接入进来时的channel对象
     * 也可以建立map结构模拟不同的消息群
     */
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
