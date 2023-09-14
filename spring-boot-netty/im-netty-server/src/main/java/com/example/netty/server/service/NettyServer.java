package com.example.netty.server.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @description: 主启动类
 * @Date 2023/3/14 10:47
 * @author iumyxF
 */
@Slf4j
@Component("nettyServer")
public class NettyServer {

    @Value("${netty.port}")
    private int port;

    @Resource
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    /**
     * boos线程组，用于接受客户端连接请求
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * worker线程组，用于处理客户端读写请求
     */
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * Channel
     */
    private Channel channel;

    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG, 1024)
                // TCP Keepalive 机制，实现 TCP 层级的心跳保活功能
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 允许较小的数据包的发送，降低延迟
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(nettyServerHandlerInitializer);
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            channel = future.channel();
            log.info("netty server 启动成功! 端口为 {}", port);
        }
    }

    /**
     * 关闭 Netty Server
     */
    @PreDestroy
    public void shutdown() {
        // 关闭 Netty Server
        if (channel != null) {
            channel.close();
        }
        // 优雅关闭两个 EventLoopGroup 对象
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
