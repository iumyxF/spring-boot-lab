package com.example.hls.converter;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/25 11:10
 */
public class Temp {

    /**
     * 1.原视频 清晰
     * 2.cmd命令转换 清晰
     * 3.javacv转换 模糊
     * 4.javacv添加参数后 清晰 但是音频 编码不确定
     */
    public static void main(String[] args) throws FrameGrabber.Exception, FrameRecorder.Exception {
        long start = System.currentTimeMillis();
        // 定义输入和输出的路径
        String input = "F:\\testData\\mediaTest\\Big\\Pandas.mp4";
        String output = "F:\\testData\\mediaTest\\Big\\javaCv3\\Pandas.m3u8";

        // 创建一个抓取器，用于读取mp4文件
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(input);
        // 开始抓取
        grabber.start();
        // 创建一个记录器，用于写入HLS格式的视频文件
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(output, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        // 设置输出的格式为hls
        recorder.setFormat("hls");

        recorder.setOption("-map", "0");
        // 设置每个切片的时长为4秒
        recorder.setOption("hls_time", "4");
        // 设置切片列表的大小为0，表示不限制
        recorder.setOption("hls_list_size", "0");
        // 设置切片文件的命名格式，使用序号作为后缀
        recorder.setOption("hls_segment_filename", "F:\\testData\\mediaTest\\Big\\javaCv3\\Pandas_%03d.ts");
        recorder.setFrameRate(grabber.getFrameRate());
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setVideoBitrate(grabber.getVideoBitrate());

        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        recorder.setAudioBitrate(grabber.getAudioBitrate());
        recorder.setAudioChannels(grabber.getAudioChannels());

        // 开始记录
        recorder.start();

        // 定义一个帧对象，用于存储抓取到的图像
        Frame frame;
        // 循环抓取图像，直到文件结束
        while ((frame = grabber.grabImage()) != null) {
            // 将抓取到的图像记录到HLS文件中
            recorder.record(frame);
        }
        // 设置记录器的时间戳，与抓取器的时间戳保持一致
        recorder.setTimestamp(grabber.getTimestamp());
        // 关闭记录器和抓取器
        recorder.close();
        grabber.close();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000 / 60 + "分钟");
    }

}
