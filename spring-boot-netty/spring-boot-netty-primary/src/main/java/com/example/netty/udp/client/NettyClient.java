package com.example.netty.udp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @Date 2023/3/13 9:41
 * @author iumyxF
 */
public class NettyClient {

    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            //设置udp channel
            b.group(group).channel(NioDatagramChannel.class)
                    .handler(new MyClientChannelInitializer());

            Channel channel = b.bind(7398).sync().channel();

            //构建UDP消息体,DatagramPacket
            InetSocketAddress address = new InetSocketAddress("127.0.0.1", 7397);
            ByteBuf buffer = Unpooled.copiedBuffer("hello server,i am udp client!", StandardCharsets.UTF_8);
            DatagramPacket datagramPacket = new DatagramPacket(buffer, address);

            channel.writeAndFlush(datagramPacket);
            channel.closeFuture().await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }
}
