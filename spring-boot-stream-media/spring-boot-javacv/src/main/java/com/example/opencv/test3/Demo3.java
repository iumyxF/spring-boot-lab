package com.example.opencv.test3;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Demo3 {

    public static void main(String[] args) throws Exception {
        // 创建一个FFmpegFrameGrabber对象，用于打开RTSP地址
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("rtsp://192.168.2.88:554/ch01");
        // 设置视频格式
        grabber.setFormat("rtsp");
        // 设置传输协议，可以是tcp或udp
        grabber.setOption("rtsp_transport", "tcp");
        // 开始捕获视频帧
        grabber.start();

        // 创建一个OpenCVFrameConverter对象，用于将Frame对象转换成Mat对象
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
        // 创建一个Java2DFrameConverter对象，用于将Frame对象转换成BufferedImage对象
        Java2DFrameConverter frameConverter = new Java2DFrameConverter();

        // 创建一个JFrame对象，用于显示视频帧
        JFrame frame = new JFrame("RTSP Demo");
        // 设置窗口的大小和位置
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        // 设置窗口的关闭操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 创建一个JLabel对象，用于显示视频帧
        JLabel label = new JLabel();
        // 将JLabel对象添加到JFrame对象中
        frame.add(label);
        // 设置窗口为可见
        frame.setVisible(true);

        // 循环获取视频帧
        while (true) {
            // 调用grabImage方法，获取一帧视频数据
            Frame videoFrame = grabber.grabImage();
            // 判断视频帧是否为空，如果为空，表示视频结束，退出循环
            if (videoFrame == null) {
                break;
            }
            // 将视频帧转换成Mat对象
            Mat mat = converter.convertToMat(videoFrame);
            // 将视频帧转换成BufferedImage对象
            BufferedImage image = frameConverter.convert(videoFrame);
            // 将BufferedImage对象设置到JLabel对象中，显示在窗口中
            label.setIcon(new ImageIcon(image));
            // 刷新窗口
            frame.repaint();
            // 等待一定的时间，控制视频的播放速度
            Thread.sleep(20);
        }

        // 停止捕获视频帧
        grabber.stop();
        // 释放资源
        grabber.release();
    }
}

