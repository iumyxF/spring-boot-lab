package com.example.netty.udp.base.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @description:
 * @Date 2023/3/13 9:41
 * @author iumyxF
 */
public class NettyServer {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    //广播
                    .option(ChannelOption.SO_BROADCAST, true)
                    //UDP读缓冲区2M
                    .option(ChannelOption.SO_RCVBUF, 2048 * 1024)
                    //UDP写缓冲区1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    .handler(new MyServerChannelInitializer());

            ChannelFuture future = b.bind(7397).sync();
            System.out.println("udp server start done");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
