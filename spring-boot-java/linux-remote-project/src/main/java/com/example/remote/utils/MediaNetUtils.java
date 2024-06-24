package com.example.remote.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author iumyx
 * @description: 网络工具类
 * @date 2023/10/30 14:17
 */
public class MediaNetUtils {

    private static final String[] COMMON_NETWORK_NAMES = {"Intel", "Realtek", "ens33", "eno", "eth", "enp", "wlan"};

    private static final String[] EXCLUDE_NETWORK_NAMES = {"docker", "VMware", "veth", "br-"};


    /**
     * 获取本机网卡
     *
     * @return NetworkInterface
     */
    public static NetworkInterface getNetworkInterface() {
        try {
            String networkName = null;
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                //如果有指定网卡，则返回指定的网卡信息
                if (StringUtils.isNotBlank(networkName) && StringUtils.containsIgnoreCase(netInterface.getDisplayName(), networkName)) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress ip = addresses.nextElement();
                        if (ip != null) {
                            if (ip instanceof Inet4Address) {
                                return netInterface;
                            }
                        }
                    }
                }
                //没有指定网卡则按照匹配规则筛选
                String displayName = netInterface.getDisplayName();
                if (!isExclude(displayName) && isLegal(displayName)) {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress ip = addresses.nextElement();
                        if (ip != null) {
                            if (ip instanceof Inet4Address) {
                                return netInterface;
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Inet6Address getLocalIpv6Address(NetworkInterface networkInterface) {
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
     * 是否在排除名单上
     *
     * @param displayName
     * @return
     */
    public static boolean isExclude(String displayName) {
        return Arrays.stream(EXCLUDE_NETWORK_NAMES)
                .anyMatch(item -> StringUtils.containsIgnoreCase(displayName, item));
    }

    /**
     * 是否合法
     *
     * @param displayName
     * @return
     */
    public static boolean isLegal(String displayName) {
        return Arrays.stream(COMMON_NETWORK_NAMES)
                .anyMatch(item -> StringUtils.containsIgnoreCase(displayName, item));
    }
}
