package com.example.netty.udp.base.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: udp消息处理
 * @Date 2023/3/13 9:42
 * @author iumyxF
 */
public class MyClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        //获取消息内容
        String msg = packet.content().toString(StandardCharsets.UTF_8);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " UDP客户端接收到消息：" + msg);
    }
}
