package com.example.media.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
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
 * @author feng
 * @description:
 * @date 2024/11/13 20:55
 */
@Slf4j
@Component("nettyServer")
public class NettyServer {

    /**
     * netty启动端口
     */
    @Value("${venus.media.netty.server.port:1553}")
    private int port;

    @Resource
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    /**
     * boos线程组，用于接受客户端连接请求
     */
    private EventLoopGroup bossGroup;

    /**
     * worker线程组，用于处理客户端读写请求
     */
    private EventLoopGroup workerGroup;

    /**
     * Channel
     */
    private Channel channel;

    /**
     * 启动netty服务
     * 使用media-server的netty
     */
    @PostConstruct
    public void start() {
        startNettyServer();
    }

    /**
     * 关闭 Netty Server
     */
    @PreDestroy
    public void shutdown() {
        shutdownNettyServer();
    }

    /**
     * 项目中的netty服务器启动
     */
    public void startNettyServer() {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                // 服务端可连接队列
                .option(ChannelOption.SO_BACKLOG, 128)
                // 解决端口占用问题
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(nettyServerHandlerInitializer);
        try {
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                channel = future.channel();
                log.info("netty server 启动成功! 端口为 {}", port);
            }
        } catch (Exception ex) {
            log.error(ex.toString(), ex);
        }

    }

    /**
     * 项目中的netty服务器关闭
     */
    public void shutdownNettyServer() {
        if (channel != null) {
            channel.close();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
}

