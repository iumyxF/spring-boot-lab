package com.example.netty.buffer;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @description: byteBuffer中粘包和半包问题
 * @Date 2023/3/6 16:43
 * @Author fzy
 */
public class StickyAndSemi {
    /**
     * 解决方法：
     * 1.通过get(index)方法遍历ByteBuffer，遇到分隔符时进行处理。注意：get(index)不会改变position的值
     * 记录该段数据长度，以便于申请对应大小的缓冲区
     * 将缓冲区的数据通过get()方法写入到target中
     * 2.调用compact方法切换模式，因为缓冲区中可能还有未读的数据
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        // 模拟粘包+半包
        buffer.put("Hello,world\nI'm iumyxF\nHo".getBytes());
        // 调用split函数处理
        handler(buffer);
        buffer.put("w are you?\n".getBytes());
        handler(buffer);

    }

    private static void handler(ByteBuffer buffer) {
        // 切换为读模式
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            // 遍历寻找分隔符
            // get(i)不会移动position
            if (buffer.get(i) == '\n') {
                // 缓冲区长度
                int length = i + 1 - buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                // 将前面的内容写入target缓冲区
                for (int j = 0; j < length; j++) {
                    // 将buffer中的数据写入target中
                    target.put(buffer.get());
                }
                target.flip();
                StringBuilder builder = new StringBuilder();
                builder.append(StandardCharsets.UTF_8.decode(target));
                System.out.println(builder);
            }
        }
        // 切换为写模式，但是缓冲区可能未读完，这里需要使用compact
        buffer.compact();
    }
}
