package com.example.rtsp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author fzy
 * @description:
 * @date 2024/11/20 9:48
 */
@Slf4j
@Component
public class NettyClient {

    @Value("${netty.host}")
    private String host;

    @Value("${netty.port}")
    private int port;

    @Resource
    private RtspClientHandler clientHandler;

    private final EventLoopGroup group = new NioEventLoopGroup();

    private Channel channel;

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(clientHandler);
            }
        });
        ChannelFuture f = b.connect(host, port).sync();
        if (f.isSuccess()) {
            channel = f.channel();
            log.info("netty client 启动成功! 连接的端口为 {}", port);
        }
    }

    @PreDestroy
    public void shutdown() {
        // 关闭 Netty Server
        if (channel != null) {
            channel.close();
        }
        group.shutdownGracefully();
    }
}
