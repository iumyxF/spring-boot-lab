package com.example.hls.converter;

import org.apache.commons.lang3.SystemUtils;
import utils.CmdUtil;

import java.io.File;
import java.io.IOException;

/**
 * @author iumyxF
 * @description: 转换速度测试
 * @date 2023/12/21 11:45
 */
public class SpeedTest {

    /**
     * 1. 测试 800M视频
     * 重新编码H264 转换时间 11分钟
     * 不编码  转换时间 1秒
     *
     * 2. 测试 2G视频
     * 重新编码H264 转换时间 非常慢
     * 不编码  转换时间 秒
     *
     * @param args
     */
    public static void main(String[] args) {
        //File video = new File("F:\\testData\\mediaTest\\Big\\Pandas.mp4");
        //File video = new File("F:\\testData\\mediaTest\\4K\\Planet.Earth.mkv");
        File video = new File("F:\\testData\\mediaTest\\Big\\Pandas.mp4");

        convertHls(video);
    }

    /**
     * 800M视频
     * 重新编码H264 转换时间 11分钟
     * 不编码  -codec:v copy -codec:a copy  转换时间 1秒
     *
     * @param file
     */
    public static void convertHls(File file) {
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        //tsName = F:\testData\mediaTest\AVI\AV36_1_%03d.ts
        String tsName = file.getParent() + "\\" + fileName + "_%03d.ts";
        //outputFile = F:\testData\mediaTest\AVI\AV36_1.m3u8
        String outputFile = file.getParent() + "\\" + fileName + ".m3u8";
        String cmd = String.format("ffmpeg -i %s " +
                //"-codec:v libx264 -codec:a aac " +
                //"-codec:v libx265 -codec:a aac " +
                "-codec:v copy -codec:a copy " +
                //"-codec:v copy -preset:v ultrafast -codec:a copy " +
                "-map 0 " +
                "-f hls " +
                "-hls_time 4 " +
                "-hls_list_size 0 " +
                "-hls_segment_filename %s %s", file.getPath(), tsName, outputFile);
        System.out.println(cmd);
        try {
            if (SystemUtils.IS_OS_WINDOWS) {
                System.out.println("windows环境");
                ProcessBuilder builder = CmdUtil.exec(cmd);
                builder.start();
            } else if (SystemUtils.IS_OS_LINUX) {
                System.out.println("linux环境");
                ProcessBuilder builder = CmdUtil.exec("sh", "-c", cmd);
                builder.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
