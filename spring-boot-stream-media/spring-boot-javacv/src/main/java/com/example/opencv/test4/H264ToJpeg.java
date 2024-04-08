package com.example.opencv.test4;

import cn.hutool.core.io.IoUtil;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author fzy
 * @description:
 * @date 2024/4/7 13:39
 */
public class H264ToJpeg {

    /**
     * 将视频转换成jpeg ffmpeg -i test_264_video.264 -ss 00:00:00 -y -f image2 -vsync 2 F:\testData\other\jpegto264\%d.jpg
     * 将视频的第一帧转换成jpeg ffmpeg -i test_264_video.264 -y -f image2 -t 0.001 -s 1024*600 jpeg_test.jpeg
     */
    private static final String VIDEO_PATH = "F:\\testData\\other\\jpegto264\\test_264_video.264";

    private static final String JPEG_PATH = "F:\\testData\\other\\jpegto264";

    public static void main(String[] args) throws IOException {
        ////读取本地视频获取H264流
        //byte[] h264 = readVideo();
        ////将H264转换成jpeg流
        //byte[] jpeg = convertH264ToJpeg(h264);
        ////将流写到本地文件保存为jpeg图片
        //writeJpegToFile(jpeg);

        testConvert();
    }

    private static byte[] convertH264ToJpeg(byte[] data) {
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            grabber = new FFmpegFrameGrabber(new ByteArrayInputStream(data));
            grabber.setFormat("H264");
            grabber.start();

            recorder = new FFmpegFrameRecorder(baos, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_MJPEG);
            recorder.setFormat("image2");
            recorder.setFrameRate(grabber.getVideoFrameRate());
            recorder.start();
            Frame frame = grabber.grab();
            recorder.record(frame);
            recorder.stop();
            grabber.stop();
        } catch (FFmpegFrameGrabber.Exception | FFmpegFrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private static byte[] readVideo() throws IOException {
        return IoUtil.readBytes(Files.newInputStream(Paths.get(VIDEO_PATH)));
    }

    private static void writeJpegToFile(byte[] jpeg) throws IOException {
        String fileName = JPEG_PATH + File.separator + "jpeg_" + System.currentTimeMillis() + ".jpeg";
        IoUtil.write(Files.newOutputStream(Paths.get(fileName)), true, jpeg);
    }

    /**
     * F:\testData\other\jpegto264\test_264_video.264
     * F:\testData\other\jpegto264\%d.jpeg
     */
    public static void testConvert() throws IOException {
        byte[] data = IoUtil.readBytes(Files.newInputStream(Paths.get("F:\\testData\\other\\jpegto264\\test_264_video.264")));

        //FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("F:\\testData\\other\\jpegto264\\test_264_video.264");

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(new ByteArrayInputStream(data));
        //grabber.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);

        try {
            grabber.start();
            Frame frame;
            int frameNumber = 0;
            while ((frame = grabber.grabImage()) != null) {
                String fileName = String.format("F:\\testData\\other\\jpegto264\\%d.jpeg", frameNumber++);
                FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(fileName, grabber.getImageWidth(), grabber.getImageHeight());
                recorder.setFormat("image2");
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_MJPEG);
                recorder.start();
                recorder.record(frame);
                recorder.stop();
                recorder.release();
            }
            grabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
