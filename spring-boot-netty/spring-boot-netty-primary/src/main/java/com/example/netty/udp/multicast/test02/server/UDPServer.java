package com.example.netty.udp.multicast.test02.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * @author feng
 * @description:
 * @date 2024/11/12 21:25
 */
public class UDPServer {

    private final String multicastAddress = "238.255.255.250";
    private final int port = 12345;

    public void start() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new ChannelInitializer<DatagramChannel>() {
                        @Override
                        protected void initChannel(DatagramChannel ch) {
                            ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new MulticastHandler());
                        }
                    });

            // Join the multicast group
            ChannelFuture f = b.bind(port).sync();
            DatagramChannel channel = (DatagramChannel) f.channel();
            NetworkInterface ni = NetworkInterface.getByName("wlan0"); // 请根据实际情况替换网络接口名称
            channel.joinGroup(new InetSocketAddress(multicastAddress, port), ni).sync();

            f.channel().closeFuture().await();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new UDPServer().start();
    }

}
