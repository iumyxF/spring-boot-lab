package com.example.netty.udp.multicast.test02.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * @author feng
 * @description:
 * @date 2024/11/12 21:29
 */
public class UDPClient {

    private final String multicastAddress = "238.255.255.250";
    private final int port = 12345;
    private final DatagramChannel channel;

    public UDPClient() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new UDPClientHandler());

            ChannelFuture f = b.bind(0).sync();
            channel = (DatagramChannel) f.channel();

            NetworkInterface ni = NetworkInterface.getByName("wlan0"); // 请根据实际情况替换网络接口名称
            channel.joinGroup(new InetSocketAddress(multicastAddress, port), ni).sync();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } finally {
            // group.shutdownGracefully();  // 不在此处关闭，保持连接
        }
    }

    public void sendMessage(String message) {
        byte[] msgBytes = message.getBytes(CharsetUtil.UTF_8);
        channel.writeAndFlush(new DatagramPacket(Unpooled.wrappedBuffer(msgBytes),
                new InetSocketAddress(multicastAddress, port)));
    }

    public static void main(String[] args) throws InterruptedException {
        UDPClient udpClient = new UDPClient();
        udpClient.sendMessage("hello");
    }
}

