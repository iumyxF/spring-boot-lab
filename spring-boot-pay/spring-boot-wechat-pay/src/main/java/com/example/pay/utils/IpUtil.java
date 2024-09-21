package com.example.pay.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author feng
 * @description: 获取IP地址工具类
 * @date 2024/7/18 21:27
 */
public class IpUtil {

    private static final String LOOP_ADDRESS = "127.0.0.1";

    private static final String UNKNOWN = "unknown";

    private static final Integer IP_ADDRESS_MAX_LENGTH = 15;

    private static final String COMMA = ",";

    private static final String X_FORWARDER_FOR = "x-forwarded-for";

    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    private static final String X_REAL_IP = "X-Real-IP";

    /**
     * 获取IP地址信息
     *
     * @param request 请求
     * @return IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader(X_FORWARDER_FOR);
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader(PROXY_CLIENT_IP);
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader(WL_PROXY_CLIENT_IP);
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (LOOP_ADDRESS.equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    if (null != inet) {
                        ipAddress = inet.getHostAddress();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > IP_ADDRESS_MAX_LENGTH) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(COMMA) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(COMMA));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 获取网关的IP地址
     *
     * @param request 请求
     * @return IP地址
     */
    public static String getGatewayIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst(X_FORWARDER_FOR);
        if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(COMMA)) {
                ip = ip.split(COMMA)[0];
            }
        }
        if (ipIsEmpty(ip)) {
            ip = headers.getFirst(PROXY_CLIENT_IP);
        }
        if (ipIsEmpty(ip)) {
            ip = headers.getFirst(WL_PROXY_CLIENT_IP);
        }
        if (ipIsEmpty(ip)) {
            ip = headers.getFirst(HTTP_CLIENT_IP);
        }
        if (ipIsEmpty(ip)) {
            ip = headers.getFirst(HTTP_X_FORWARDED_FOR);
        }
        if (ipIsEmpty(ip)) {
            ip = headers.getFirst(X_REAL_IP);
        }
        if (ipIsEmpty(ip)) {
            if (null != request.getRemoteAddress()) {
                ip = request.getRemoteAddress().getAddress().getHostAddress();
            }
        }
        return ip;
    }

    private static boolean ipIsEmpty(String ip) {
        return ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip);
    }
}
