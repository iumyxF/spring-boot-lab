package com.example.crawler.utils;

import com.example.crawler.model.download.DownloadRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/19 17:22
 */
public class DownloadUtils {

    private static final Logger logger = LoggerFactory.getLogger(DownloadUtils.class);

    public static void downloadFile(DownloadRequest request) {
        logger.info("开始下载歌曲:{},保存文件夹路径:{}", request.getFileName(), request.getFilePath());
        String savePath = request.getFilePath() + "//" + request.getFileName();
        try {
            // 打开URL连接
            URLConnection conn = new URL(request.getDownloadUrl()).openConnection();
            // 获取内容长度
            int contentLength = conn.getContentLength();
            if (contentLength > 0) {
                // 获取输入流
                InputStream is = conn.getInputStream();
                // 创建输入通道
                ReadableByteChannel rbc = Channels.newChannel(is);
                // 创建输出流
                FileOutputStream fos = new FileOutputStream(savePath);
                // 创建输出通道
                FileChannel fc = fos.getChannel();
                // 传输字节
                fc.transferFrom(rbc, 0, Long.MAX_VALUE);
                // 关闭流和通道
                fos.close();
                fc.close();
                rbc.close();
                logger.info("{},下载成功", request.getFileName());
            } else {
                logger.info("{},文件链接失效,跳过下载", request.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
