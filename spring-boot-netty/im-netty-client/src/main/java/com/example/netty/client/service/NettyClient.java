package com.example.netty.client.service;

import com.example.netty.common.codec.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description: netty客户端
 * @Date 2023/3/14 15:47
 * @author iumyxF
 */
@Slf4j
@Component
public class NettyClient {

    /**
     * 重连频率，单位 秒
     */
    private final static Integer RECONNECT_SECONDS = 20;

    /**
     * netty服务端地址
     */
    @Value("${netty.server.host}")
    private String serverHost;

    /**
     * netty服务端端口
     */
    @Value("${netty.server.port}")
    private Integer serverPort;

    @Resource
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;

    /**
     * 线程组，用于客户端对服务端的链接、数据读写
     */
    private EventLoopGroup eventGroup = new NioEventLoopGroup();
    /**
     * Netty Client Channel
     */
    private volatile Channel channel;

    @PostConstruct
    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(serverHost, serverPort)
                // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 允许较小的数据包的发送，降低延迟
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(nettyClientHandlerInitializer);
        // 链接服务器，并异步等待成功，即启动客户端
        bootstrap.connect().addListener((ChannelFutureListener) future -> {
            if (!future.isSuccess()) {
                log.error("[start][Netty Client 连接服务器({}:{}) 失败]", serverHost, serverPort);
                //尝试重新连接
                reconnect();
                return;
            }
            channel = future.channel();
            log.info("[start][Netty Client 连接服务器({}:{}) 成功]", serverHost, serverPort);
        });
    }

    /**
     * 重连,每20s尝试重连一次
     */
    public void reconnect() {
        eventGroup.schedule(() -> {
            log.info("[reconnect][开始重连]");
            try {
                start();
            } catch (Exception e) {
                log.error("[reconnect][重连失败]", e);
            }
        }, RECONNECT_SECONDS, TimeUnit.SECONDS);
        log.info("[reconnect][{} 秒后将发起重连]", RECONNECT_SECONDS);
    }

    /**
     * 关闭netty服务
     */
    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            channel.close();
        }
        eventGroup.shutdownGracefully();
    }

    /**
     * 发送消息
     *
     * @param invocation 消息体
     */
    public void send(Invocation invocation) {
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
}
