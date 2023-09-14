package com.example.netty.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @description: netty服务端 交给spring容器管理
 * @Date 2023/3/13 14:18
 * @author iumyxF
 */
@Slf4j
@Component("nettyServer")
public class NettyServer {

    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup();

    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;

    /**
     * 启动netty服务
     *
     * @param address InetSocketAddress
     * @return
     */
    public ChannelFuture bing(InetSocketAddress address) {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup, workerGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //自定义处理器
                    .childHandler(new MyServerHandler());
            channelFuture = bootstrap.bind(address).syncUninterruptibly();
            channel = channelFuture.channel();
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                log.info("server start done");
            } else {
                log.error("server start error");
            }
        }
        return channelFuture;
    }

    /**
     * 停止netty服务
     */
    public void destroy() {
        if (null == channel) {
            return;
        }
        channel.close();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    /**
     * 获取netty的channel
     *
     * @return Channel
     */
    public Channel getChannel() {
        return channel;
    }

}
