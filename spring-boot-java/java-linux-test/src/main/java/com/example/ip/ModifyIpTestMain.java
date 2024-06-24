package com.example.ip;

/**
 * @author iumyx
 * @description: 测试修改IP
 * @date 2024/6/17 16:01
 */
public class ModifyIpTestMain {

    /**
     * 修改Linux的IP
     * 1. 编写Shell脚本，通过Java 传递参数执行
     * 2. 获取执行结果
     * sudo ./modifyIp.sh 192.168.2.192 255.255.255.0 192.168.2.1
     * sudo ./modifyIp.sh 192.168.2.194 255.255.255.0 192.168.2.1
     */
    public static void main(String[] args) {
        // centos 下执行shell sudo ./modifyIp.sh xxx xxx xxx xxx,
        // 可能出现command not found，要在/etc/sudoers 文件中 Defaults secure_path = /sbin:/bin:/usr/sbin:/usr/bin:/user/local/bin 加上"/user/local/bin"
        convert("255.255.255.0");
        convert("255.255.0.0");
    }

    /**
     * NETMASK to PREFIX
     */
    public static void convert(String netmask) {
        //String netmask = "255.255.254.0";
        String[] data = netmask.split("\\.");
        int len = 0;
        for (String n : data) {
            len += (8 - Math.log(256 - Integer.parseInt(n)) / Math.log(2));
        }
        System.out.println(len);
    }
}
