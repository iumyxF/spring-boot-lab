package com.example.netty.udp.multicast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.*;
import java.util.Enumeration;

/**
 * @author iumyxF
 * @description: 使用Netty监听组播信息
 * @date 2023/10/30 9:19
 */
public class MulticastNetty {

    private final String networkInterfaceName;
    private final String ipv4MulticastAddress;
    private final Integer multicastPort;

    public MulticastNetty(String networkInterfaceName, String ipv4MulticastAddress, Integer multicastPort) {
        this.networkInterfaceName = networkInterfaceName;
        this.ipv4MulticastAddress = ipv4MulticastAddress;
        this.multicastPort = multicastPort;
    }

    public static void main(String[] args) {
        MulticastNetty multicastNetty = new MulticastNetty("eth2", "225.25.25.25", 7574);
        multicastNetty.startServer();
    }

    public void startServer() {
        NetworkInterface networkInterface = NetUtils.getNetworkInterfaceByName(networkInterfaceName);
        Inet6Address networkInterfaceAddress = getLocalAddress(networkInterface);
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channelFactory((ChannelFactory<NioDatagramChannel>) () -> new NioDatagramChannel(InternetProtocolFamily.IPv4))
                    .localAddress(networkInterfaceAddress, multicastPort)
                    .option(ChannelOption.IP_MULTICAST_IF, networkInterface)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .handler(new ChannelInitializer<NioDatagramChannel>() {
                        @Override
                        public void initChannel(NioDatagramChannel ch) {
                            ch.pipeline().addLast(new MyMulticastHandler());
                        }
                    });
            NioDatagramChannel ch = (NioDatagramChannel) b.bind(multicastPort).sync().channel();
            InetSocketAddress multicastSocketAddress = new InetSocketAddress(ipv4MulticastAddress, multicastPort);
            ch.joinGroup(multicastSocketAddress, networkInterface).sync();
            System.out.println("server done");
            ch.closeFuture().await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }


    /**
     * 根据网卡信息获取Inet6Address
     *
     * @param networkInterface 网卡
     * @return Inet6Address
     */
    private static Inet6Address getLocalAddress(NetworkInterface networkInterface) {
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
            InetAddress address = addresses.nextElement();
            if (address instanceof Inet6Address) {
                return (Inet6Address) address;
            }
        }
        throw new RuntimeException();
    }
}
