package com.example.hls.converter;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.io.File;
import java.util.ArrayList;

/**
 * @author iumyxF
 * @description: 兼容测试
 * @date 2023/12/22 11:20
 */
public class CompatibleTest {

    /**
     * 部分视频转换时 不能直接使用-codec:v copy -codec:a copy
     * 一般来说，HLS 协议支持 H.264 和 H.265（HEVC）两种视频编码格式
     *
     * AV36_1.AVI不兼容HLS
     * flvVideo.flv不兼容HLS
     * mkvVideo.mkv不兼容HLS
     * movVideo.mov兼容HLS
     * mp4Video.mp4兼容HLS
     * wmvVideo.wmv不兼容HLS
     */
    public static void main(String[] args) {
        ArrayList<File> files = new ArrayList<>();
        File aviFile = new File("F:\\testData\\mediaTest\\AVI\\AV36_1.AVI");
        File flvFile = new File("F:\\testData\\mediaTest\\FLV\\flvVideo.flv");
        File mkvFile = new File("F:\\testData\\mediaTest\\MKV\\mkvVideo.mkv");
        File movFile = new File("F:\\testData\\mediaTest\\MOV\\movVideo.mov");
        File mp4File = new File("F:\\testData\\mediaTest\\MP4\\mp4Video.mp4");
        File wmvFile = new File("F:\\testData\\mediaTest\\WMV\\wmvVideo.wmv");
        files.add(aviFile);
        files.add(flvFile);
        files.add(mkvFile);
        files.add(movFile);
        files.add(mp4File);
        files.add(wmvFile);
        for (File file : files) {
            judgeHls(file);
        }
    }

    public static void judgeHls(File file) {
        // 创建FFmpegFrameGrabber对象
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(file.getPath())) {
            // 开始读取视频
            grabber.start();
            // 获取视频的编码格式
            String codec = grabber.getVideoCodecName();
            // 判断视频的编码格式是否符合HLS协议
            if (codec.equalsIgnoreCase("h264") || codec.equalsIgnoreCase("hevc")) {
                System.out.println(file.getName() + "兼容HLS");
            } else {
                System.out.println(file.getName() + "不兼容HLS");
            }
            // 释放资源
            grabber.stop();
        } catch (FrameGrabber.Exception e) {
            throw new RuntimeException(e);
        }
    }

}
