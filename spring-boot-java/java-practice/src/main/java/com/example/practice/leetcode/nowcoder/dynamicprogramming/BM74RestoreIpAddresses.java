package com.example.practice.leetcode.nowcoder.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/12/24 16:45
 */
public class BM74RestoreIpAddresses {

    /*
    现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
    例如：
    给出的字符串为"25525522135",
    返回["255.255.22.135", "255.255.221.35"]. (顺序没有关系)

    动态规划
    dp[i] = ?
    递推公式 = ?

    dfs

     */

    public ArrayList<String> restoreIpAddresses(String s) {
        //dfs
        ArrayList<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        dfs(s, "", 0, 0, res);
        return res;
    }

    public void dfs(String str, String path, int index, int segmentCount, List<String> res) {
        if (segmentCount == 4 && index == str.length()) {
            res.add(path.substring(0, path.length() - 1));
            return;
        }
        if (segmentCount > 4) {
            return;
        }
        for (int i = 1; i <= 3 && i + index <= str.length(); i++) {
            String temp = str.substring(index, index + i);
            if (verify(temp)) {
                dfs(str, path + temp + ".", index + i, segmentCount + 1, res);
            }
        }
    }

    public boolean verify(String str) {
        // ip地址不能以0开头，除非是单0
        if (str.length() > 1 && str.charAt(0) == '0') {
            return false;
        }
        int num = Integer.parseInt(str);
        return num >= 0 && num <= 255;
    }

    public static void main(String[] args) {
        BM74RestoreIpAddresses s = new BM74RestoreIpAddresses();
        List<String> ipAddresses = s.restoreIpAddresses("25525522135");
        for (String ip : ipAddresses) {
            System.out.println(ip);
        }

        System.out.println(" ===== ip test =====");
        String ip = "192.168.2.123";
        Long anInt = s.ipv4ToInt(ip);
        System.out.println(anInt);
        System.out.println(s.solveIpv4(anInt));
    }

    public Long ipv4ToInt(String ip) {
        long ipValue = 0;
        String[] split = ip.split("\\.");
        int pow = split.length - 1;
        for (String s : split) {
            // 192.168.2.196 = 192*256^3 + 168*256^2 + ... + 196*256^0
            ipValue += Integer.parseInt(s) * Math.pow(256, pow--);
        }
        return ipValue;
    }

    public String solveIpv4(Long num) {
        // 右移8位
        int a = (int) (num & 0xFF);
        int b = (int) ((num >> 8) & 0xFF);
        int c = (int) ((num >> 16) & 0xFF);
        int d = (int) ((num >> 24) & 0xFF);
        return a + "." +
                b + "." +
                c + "." +
                d;
    }
}
