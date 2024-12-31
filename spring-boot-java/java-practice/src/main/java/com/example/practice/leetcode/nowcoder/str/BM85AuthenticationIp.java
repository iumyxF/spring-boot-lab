package com.example.practice.leetcode.nowcoder.str;

/**
 * @author fzy
 * @description:
 * @date 30/12/2024 上午9:56
 */
public class BM85AuthenticationIp {

    /*
    编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址

    IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
    同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。

    IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。
    比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。
    而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。
    所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。

    然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。
    比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
    同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。

    ip地址的类型，可能为
    IPv4,   IPv6,   Neither
    说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。

    "2001:0db8:85a3:0:0:8A2E:0370:7334"
    "IPv6"
    说明：这是一个有效的 IPv6 地址, 所以返回 "IPv6"

    "256.256.256.256"
    "Neither"
    这个地址既不是 IPv4 也不是 IPv6 地址
     */

    public String solve(String ip) {
        if (ip.contains(".")) {
            return solveIpv4(ip);
        } else {
            return solveIpv6(ip);
        }
    }

    public String solveIpv4(String ip) {
        if (ip.lastIndexOf(".") == ip.length() - 1) {
            return "Neither";
        }
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            return "Neither";
        }
        for (String item : split) {
            if (item.length() == 0 || item.length() > 3) {
                return "Neither";
            }
            // 两位以上，第一位字符不能是0
            if (item.length() > 1 && item.charAt(0) == '0') {
                return "Neither";
            }
            // 范围要在0 ~ 255
            for (int i = 0; i < item.length(); i++) {
                if (!isNum(item.charAt(i))) {
                    return "Neither";
                }
            }
            int vale = Integer.parseInt(item);
            if (vale < 0 || vale > 255) {
                return "Neither";
            }
        }
        return "IPv4";
    }

    public String solveIpv6(String ip) {
        if (ip.lastIndexOf(":") == ip.length() - 1) {
            return "Neither";
        }
        String[] split = ip.split(":");
        if (split.length != 8) {
            return "Neither";
        }
        for (String item : split) {
            if (item.length() == 0 || item.length() > 4) {
                return "Neither";
            }
            // 范围要在0 ~ 65536
            for (int i = 0; i < item.length(); i++) {
                char c = item.charAt(i);
                if (!isNum(c) && !isLetter(c)) {
                    return "Neither";
                }
            }
            int vale = Integer.parseInt(item, 16);
            if (vale < 0 || vale > 65535) {
                return "Neither";
            }
        }
        return "IPv6";
    }

    /**
     * a ~ f and A ~ F
     */
    private boolean isLetter(char c) {
        return (c >= 97 && c <= 102) || (c >= 65 && c <= 70);
    }

    /**
     * 0 ~ 9
     */
    private boolean isNum(char c) {
        return c >= 48 && c <= 57;
    }

    public static void main(String[] args) {
        BM85AuthenticationIp s = new BM85AuthenticationIp();
        //System.out.println(s.solve("1a1.4.5.6"));
        //System.out.println(s.solve("2001:0db8:85a3:0:0:8A2E:0370:7334"));
        System.out.println(s.solve("2001:0db8:85a3:0:0:8A2E:0370:7334:"));

    }
}
