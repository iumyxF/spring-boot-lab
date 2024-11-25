package com.example.netty.mediaclient.client;

import com.example.netty.mediaclient.entities.MediaBinaryData;
import com.example.netty.mediaclient.utils.JacksonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

/**
 * @author fzy
 * @description:
 * @date 2024/11/21 16:58
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final String id = String.valueOf(System.currentTimeMillis() % 1000);

    ///**
    // * 227.28.116.245:54714
    // */
    //private static final String groupName = "234.114.240.238:65339";

    @Value("${client.groupName}")
    private String groupName;

    /**
     * 当通道就绪就会触发
     *
     * @param ctx 上下文channel
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        HashMap<String, Object> registerMap = new HashMap<>(1);
        registerMap.put("head", "gonsin");
        HashMap<String, Object> deviceMap = new HashMap<>(1);
        deviceMap.put("id", id);
        registerMap.put("device", deviceMap);
        HashMap<String, Object> cmdMap = new HashMap<>(1);
        cmdMap.put("cmd", "reg");
        registerMap.put("cmd", cmdMap);
        HashMap<String, Object> argsMap = new HashMap<>(2);
        argsMap.put("group_name", groupName);
        argsMap.put("sender", false);
        registerMap.put("args", argsMap);
        String msg = JacksonUtil.writeValueAsString(registerMap);
        ctx.writeAndFlush(msg);
    }

    /**
     * 当通道有读取事件时，会触发
     *
     * @param ctx 上下文channel
     * @param msg 消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //log.info("channel read ...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 空闲时，向服务端发起一次心跳
        if (evt instanceof IdleStateEvent) {
            MediaBinaryData mediaBinaryData = new MediaBinaryData();
            mediaBinaryData.setHead((byte) '$');
            mediaBinaryData.setChannel((byte) 0);
            mediaBinaryData.setType((byte) 36);
            mediaBinaryData.setId(Integer.parseInt(id));
            mediaBinaryData.setCodeType(0);
            mediaBinaryData.setLength(1);
            mediaBinaryData.setData(new byte[0]);
            ctx.writeAndFlush(mediaBinaryData);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
