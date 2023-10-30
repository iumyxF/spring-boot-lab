package com.example.netty.udp.multicast;

import java.net.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author iumyxF
 * @description: 不使用netty监听组播信息
 * @date 2023/10/26 15:47
 */
public class MulticastNonNetty {

    public void start() throws Exception {
        MulticastSocket ds = null;
        DatagramPacket packet;
        // 存储发来的消息
        byte[] buf = new byte[1024];
        try {
            //使用多播
            ds = new MulticastSocket(7574);
            InetAddress receiveAddress = InetAddress.getByName("225.25.25.25");
            //获取有效网卡地址
            List<NetworkInterface> addressList = NetUtils.getNetworkInterfaces();
            //端口不冲突就行
            int port = 10009;
            NetworkInterface ni = null;
            assert addressList != null;
            for (NetworkInterface networkInterface : addressList) {
                if ("eth2".equals(networkInterface.getName())) {
                    ni = networkInterface;
                    break;
                }
            }
            InetSocketAddress inetSocketAddress = new InetSocketAddress(receiveAddress, port);
            //将有效网卡加入组播
            ds.joinGroup(inetSocketAddress, ni);
            packet = new DatagramPacket(buf, buf.length);
            while (true) {
                ds.receive(packet);
                buf = Arrays.copyOfRange(packet.getData(), 0, packet.getLength());
                System.out.println(Arrays.toString(buf));
            }
        } catch (Exception e) {
            System.out.println("寻址监听出错" + e);
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }

    public static void main(String[] args) {
        MulticastNonNetty main2 = new MulticastNonNetty();
        try {
            main2.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
