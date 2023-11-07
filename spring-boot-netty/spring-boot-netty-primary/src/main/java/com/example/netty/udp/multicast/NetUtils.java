package com.example.netty.udp.multicast;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author iumyxF
 * @description: 网卡工具类
 * @date 2023/10/30 9:41
 */
public class NetUtils {

    private final static String LOCAL_IP = "127.0.0.1";

    /**
     * 根据网卡名字查询网卡对象
     *
     * @param networkInterfaceName 网卡名字
     * @return NetworkInterface
     */
    public static NetworkInterface getNetworkInterfaceByName(String networkInterfaceName) {
        try {
            return NetworkInterface.getByName(networkInterfaceName);
        } catch (SocketException e) {
            throw new RuntimeException();
        }
    }

    public static Inet6Address getLocalAddress(NetworkInterface networkInterface) {
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
            InetAddress address = addresses.nextElement();
            if (address instanceof Inet6Address) {
                return (Inet6Address) address;
            }
        }
        throw new RuntimeException();
    }

    /**
     * 获取本机有效所有网卡地址
     *
     * @return List<NetworkInterface> ip列表
     * @throws
     */
    public static List<NetworkInterface> getNetworkInterfaces() throws Exception {
        List<NetworkInterface> localIPlist = new ArrayList<>();
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        if (interfs == null) {
            return null;
        }
        while (interfs.hasMoreElements()) {
            NetworkInterface element = interfs.nextElement();
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress in = addresses.nextElement();
                if (in instanceof Inet4Address) {
                    if (!LOCAL_IP.equals(in.getHostAddress())) {
                        localIPlist.add(element);
                    }
                }
            }
        }
        return localIPlist;
    }

    public static NetworkInterface getNetworkInterface() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                if (!netInterface.getDisplayName().contains("Intel")
                        && !netInterface.getDisplayName().contains("Realtek")
                        && !netInterface.getDisplayName().contains("ens33")) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip != null) {
                        if (ip instanceof Inet4Address) {
                            return netInterface;
                        }
                    }
                }
                break;
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
