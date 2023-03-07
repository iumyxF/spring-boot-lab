package com.example.netty.network.nonblocking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @description: 使用NIO对文件进行复制
 * @Date 2023/3/7 13:43
 * @Author fzy
 */
public class NioCopyFile {

    private static final Logger log = LoggerFactory.getLogger(NioCopyFile.class);

    public static void main(String[] args) {
        //需要使用绝对路径，不能使用相对路径
        String realResPath = "";
        String realDistPath = "";
        fileCopy(realResPath, realDistPath);
    }

    /**
     * 文件赋值
     *
     * @param src  源文件路径
     * @param dist 目标路径
     */
    public static void fileCopy(String src, String dist) {
        try (FileInputStream fis = new FileInputStream(src); FileOutputStream fos = new FileOutputStream(dist)) {
            //获取文件输入流和channel
            FileChannel fisChannel = fis.getChannel();
            //获取文件输出流和channel
            FileChannel fosChannel = fos.getChannel();
            //创建buffer缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                int read = fisChannel.read(buffer);
                if (read == -1) {
                    break;
                }
                //读写切换
                buffer.flip();
                //将内容写入fileOutPutChannel
                fosChannel.write(buffer);
                //清空buffer
                buffer.clear();
            }
            log.info("文件复制完毕...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
