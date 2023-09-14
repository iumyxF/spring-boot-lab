package com.example.netty.file.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @description:
 * @Date 2023/3/11 11:01
 * @author iumyxF
 */
public class NettyServer {
    /**
     * 配置服务端NIO线程组
     */
    private EventLoopGroup parentGroup = new NioEventLoopGroup();
    private EventLoopGroup childGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture bing(int port) {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    //非阻塞模式
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new MyServerChannelInitializer());
            channelFuture = b.bind(port).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != channelFuture && channelFuture.isSuccess()) {
                System.out.println("server start done");
            } else {
                System.out.println("server start error");
            }
        }
        return channelFuture;
    }

    public void destroy() {
        if (null == channel) {
            return;
        }
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }

}
