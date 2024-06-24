package com.example.ip.networkCard;


import java.net.NetworkInterface;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/19 10:40
 */
public class NetworkCardMain {
    public static void main(String[] args) {
        String networkName = null;
        NetworkInterface anInterface = MediaNetUtils.getNetworkInterface();
        if (null != anInterface) {
            networkName = anInterface.getDisplayName();
        }
        System.out.println(networkName);
    }
}
