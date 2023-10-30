package com.example.netty.udp.multicast.test;

import com.example.netty.udp.multicast.NetUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;

import java.net.Inet6Address;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;

/**
 * @author fzy
 * @description: EpollEventLoopGroup 只能用于Linux环境下
 * @date 2023/10/30 10:14
 */
public class Test02 {

    private final NetworkInterface networkInterface;
    private final Inet6Address networkInterfaceAddress;
    private final int socketBufferSize;
    private final String multicastGroupIpv4Address;
    private final int multicastGroupPort;

    public Test02(String networkInterfaceName, int socketBufferSize, String multicastGroupIpv4Address, int multicastGroupPort) {
        this.networkInterface = NetUtils.getNetworkInterfaceByName(networkInterfaceName);
        this.networkInterfaceAddress = NetUtils.getLocalAddress(this.networkInterface);
        this.socketBufferSize = socketBufferSize;
        this.multicastGroupIpv4Address = multicastGroupIpv4Address;
        this.multicastGroupPort = multicastGroupPort;
    }

    public static void main(String[] args) {
        Test02 listener = new Test02("eth2", 16777216, "225.25.25.25", 7574);
        try {
            listener.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(new EpollEventLoopGroup())
                .channel(EpollDatagramChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.IP_MULTICAST_IF, this.networkInterface)
                .option(ChannelOption.SO_RCVBUF, this.socketBufferSize)
                .localAddress(this.networkInterfaceAddress, multicastGroupPort)
                .handler(new ChannelInitializer<EpollDatagramChannel>() {
                    @Override
                    protected void initChannel(EpollDatagramChannel datagramChannel) {
                        datagramChannel.pipeline().addLast(new MulticastHandler());
                    }
                });
        EpollDatagramChannel channel = (EpollDatagramChannel) bootstrap.bind(multicastGroupPort).sync().channel();
        InetSocketAddress multicastSocketAddress = new InetSocketAddress(multicastGroupIpv4Address, multicastGroupPort);
        ChannelFuture channelFuture = channel.joinGroup(multicastSocketAddress, this.networkInterface).sync();
        channelFuture.addListener((ChannelFutureListener) listener -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Working...");
            }
        });
    }

    private static class MulticastHandler extends SimpleChannelInboundHandler<DatagramPacket> {
        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, DatagramPacket packet) throws Exception {
            System.out.println(packet);
        }
    }
}
