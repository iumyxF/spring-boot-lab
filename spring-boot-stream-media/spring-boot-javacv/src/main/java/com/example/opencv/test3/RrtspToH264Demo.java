package com.example.opencv.test3;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class RrtspToH264Demo {

    public static void main(String[] args) throws Exception {
        // 创建一个FFmpegFrameGrabber对象，用于打开RTSP地址
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("rtsp://192.168.2.88:554/ch01");
        // 设置视频格式
        grabber.setFormat("rtsp");
        // 设置传输协议，可以是tcp或udp
        grabber.setOption("rtsp_transport", "tcp");
        // 开始捕获视频帧
        grabber.start();

        // 创建一个FFmpegFrameRecorder对象，用于写入H264编码数据
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("F:\\testData\\output.h264", grabber.getImageWidth(), grabber.getImageHeight());
        // 设置视频编码器
        recorder.setVideoCodecName("libx264");
        // 设置视频格式
        recorder.setFormat("h264");
        // 设置视频帧率
        recorder.setFrameRate(grabber.getFrameRate());
        // 设置视频比特率
        recorder.setVideoBitrate(grabber.getVideoBitrate());
        // 设置音频
        recorder.setAudioChannels(1);
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        // 开始录制视频
        recorder.start();

        // 循环获取视频帧
        while (true) {
            // 调用grabFrame方法，获取一帧视频数据
            Frame videoFrame = grabber.grabFrame();
            // 判断视频帧是否为空，如果为空，表示视频结束，退出循环
            if (videoFrame == null) {
                break;
            }
            // 调用record方法，将视频帧转换成H264编码，并写入到输出文件中
            recorder.record(videoFrame);
        }

        // 停止捕获视频帧
        grabber.stop();
        // 释放资源
        grabber.release();
        // 停止录制视频
        recorder.stop();
        // 释放资源
        recorder.release();
    }
}

