package com.example.practice.leetcode.od.array;


/**
 * @author fzy
 * @description:
 * @date 2024/11/28 15:19
 */
public class IPv4ConvertedToIntegers {

    public static void main(String[] args) {
        // 3232236231
        solution("192.168.2.199");
        resolution(3232236231L);
    }

    /**
     * 192.168.2.123
     * <p>
     * 32进制
     * 192.0.0.0 的十进制 + 168.0.0 的十进制 ... ?
     * <p>
     * 4.3.2.1 具体计算过程如下：
     * 4*256^3 + 3*256^2 + 2*256^1 + 1*256^0 = 67305985
     *
     * @param addr
     */
    public static void solution(String addr) {
        String[] split = addr.split("\\.");
        long res = 0;
        int index = 3;
        for (String s : split) {
            int i = Integer.parseInt(s);
            res += i * Math.pow(256, index--);
        }
        System.out.println(res);
    }

    /**
     * 反向解析 3232236231 解析出 192.168.2.199
     */
    public static void resolution(long value) {
        int D = (int) (value & 0xFF);
        int C = (int) ((value >> 8) & 0xFF);
        int B = (int) ((value >> 16) & 0xFF);
        int A = (int) ((value >> 24) & 0xFF);
        System.out.println(A + "." + B + "." + C + "." + D);
    }
}
