package com.example.netty.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @description: String 和 byteBuffer的转换
 * @Date 2023/3/6 16:29
 * @Author fzy
 */
public class Translate {
    public static void main(String[] args) {
        method1();
        method2();
    }

    public static void method1() {
        String str1 = "中文";
        String str2 = "";

        // 通过StandardCharsets的encode方法获得ByteBuffer
        // 此时获得的ByteBuffer为读模式，无需通过flip切换模式
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode(str1);

        // 将缓冲区中的数据转化为字符串
        // 通过StandardCharsets解码，获得CharBuffer，再通过toString获得字符串
        str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str2);
    }

    public static void method2() {
        // 准备两个字符串
        String str1 = "你好";
        String str2 = "";

        // 通过StandardCharsets的encode方法获得ByteBuffer
        // 此时获得的ByteBuffer为读模式，无需通过flip切换模式
        ByteBuffer buffer1 = ByteBuffer.wrap(str1.getBytes());

        // 将缓冲区中的数据转化为字符串
        // 通过StandardCharsets解码，获得CharBuffer，再通过toString获得字符串
        str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println(str2);
    }
}
