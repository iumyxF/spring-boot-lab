package com.example.netty.buffer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @description: byteBuffer读取文件内容
 * @Date 2023/3/6 15:47
 * @Author fzy
 */
@Slf4j
public class TestByteBuffer {

    /**
     * Java中四个流：InputStream、OutputStream、Reader、Writer
     * channel用来传输，buffer用来存储
     * 读取文件流程：获取文件流的channel，读到指定buffer中，每次读满buffer后进行切换，对文件内容进行操作。
     * <p>
     * Buffer中四个重要属性 mark、position、limit、capacity
     * 在写模式下：position指向将要写入（put）的位置，limit=capacity为buffer大小
     * 在读模式下：position指向读取（get）的第一个元素位置（一般为0），limit指向最后一个读取元素的下一个位置。
     */
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("article.txt");
        try (FileChannel channel = new FileInputStream(resource.getFile()).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(15);
            StringBuilder builder = new StringBuilder();
            while (true) {
                //从channel读数据，存储到buffer
                int len = channel.read(buffer);
                log.info("读取到的字节数{}", len);
                if (len == -1) {
                    break;
                }
                // 切换到读模式 limit=position,position=0
                buffer.flip();
                //当buffer中还有数据时,获取其中的数据
                while (buffer.hasRemaining()) {
                    builder.append(StandardCharsets.UTF_8.decode(buffer));
                }
                // 切换回写模式 position=0, limit=capacity
                buffer.clear();
            }
            System.out.println(builder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
