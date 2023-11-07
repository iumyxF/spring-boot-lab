package com.example.netty.udp.multicast;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @author iumyxF
 * @description: 消息处理器
 * @date 2023/10/30 9:35
 */
public class MyMulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket packet) {
        System.err.println("接收到的消息: " + packet);
    }
}
