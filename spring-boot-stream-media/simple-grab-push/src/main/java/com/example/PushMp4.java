package com.example;

import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fzy
 * @description: 抓取本地MP4文件 推流到SRS服务器上，目前缺点：掉帧、画面模糊
 * @date 2023/9/27 16:08
 */

public class PushMp4 {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushMp4.class);

    /**
     * 本地MP4文件的完整路径(两分零五秒的视频)
     */
    private static final String MP4_FILE_PATH = "F:\\testData\\123.mp4";

    /**
     * SRS的推流地址
     */
    private static final String SRS_PUSH_ADDRESS = "rtmp://192.168.2.180:1935/live/livestream";

    /**
     * 读取指定的mp4文件，推送到SRS服务器
     *
     * @param sourceFilePath 视频文件的绝对路径
     * @param pushAddress   推流地址
     * @throws Exception
     */
    private static void grabAndPush(String sourceFilePath, String pushAddress) throws Exception {
        // ffmepg日志级别
        avutil.av_log_set_level(avutil.AV_LOG_ERROR);
        FFmpegLogCallback.set();

        // 实例化帧抓取器对象，将文件路径传入
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(MP4_FILE_PATH);
        // 设置缓存大小，提高画质、减少卡顿花屏
        grabber.setOption("buffer_size", "1024000");
        // 读写超时，适用于所有协议的通用读写超时
        grabber.setOption("rw_timeout", "15000000");
        // 探测视频流信息，为空默认5000000微秒
        grabber.setOption("probesize", "15000000");
        // 解析视频流信息，为空默认5000000微秒
        grabber.setOption("analyzeduration", "15000000");
        // rtmp拉流缓冲区，默认3000毫秒
        grabber.setOption("rtmp_buffer", "1000");


        long startTime = 0L;

        LOGGER.info("开始初始化帧抓取器");

        // 初始化帧抓取器，例如数据结构（时间戳、编码器上下文、帧对象等），
        // 如果入参等于true，还会调用avformat_find_stream_info方法获取流的信息，放入AVFormatContext类型的成员变量oc中
        grabber.start(true);

        LOGGER.info("帧抓取器初始化完成，耗时[{}]毫秒", System.currentTimeMillis() - startTime);

        // grabber.start方法中，初始化的解码器信息存在放在grabber的成员变量oc中
        AVFormatContext avFormatContext = grabber.getFormatContext();

        // 文件内有几个媒体流（一般是视频流+音频流）
        int streamNum = avFormatContext.nb_streams();

        // 没有媒体流就不用继续了
        if (streamNum < 1) {
            LOGGER.error("文件内不存在媒体流");
            return;
        }

        // 取得视频的帧率
        double frameRate = grabber.getVideoFrameRate();

        LOGGER.info("视频帧率[{}]，视频时长[{}]秒，媒体流数量[{}]",
                frameRate,
                avFormatContext.duration() / 1000000,
                avFormatContext.nb_streams());

        /**
         * 编码器类型等于0表示视频(VIDEO)，类型等于1表示音频（AUDIO）
         * {@link org.bytedeco.ffmpeg.global.avutil}
         *
         * 编码器ID 173 & 86018(编码器ID值86018的十六进制是0x15002 == 0x15000 + 2)
         * {@link org.bytedeco.ffmpeg.global.avcodec}
         */
        // 遍历每一个流，检查其类型
        for (int i = 0; i < streamNum; i++) {
            AVStream avStream = avFormatContext.streams(i);
            AVCodecParameters avCodecParameters = avStream.codecpar();
            LOGGER.info("流的索引[{}]，编码器类型[{}]，编码器ID[{}]", i, avCodecParameters.codec_type(), avCodecParameters.codec_id());
        }

        // 视频宽度
        int frameWidth = grabber.getImageWidth();
        // 视频高度
        int frameHeight = grabber.getImageHeight();
        // 音频通道数量
        int audioChannels = grabber.getAudioChannels();

        LOGGER.info("视频宽度[{}]，视频高度[{}]，音频通道数[{}]",
                frameWidth,
                frameHeight,
                audioChannels);

        // 实例化FFmpegFrameRecorder，将SRS的推送地址传入
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(SRS_PUSH_ADDRESS,
                frameWidth,
                frameHeight,
                audioChannels);


        // 设置编码格式
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        // 设置封装格式
        recorder.setFormat("flv");
        // 一秒内的帧数
        recorder.setFrameRate(frameRate);
        // 两个关键帧之间的帧数  有些直接用原视频获取的帧率，有些是原视频帧率*2
        recorder.setGopSize(new Long(Math.round(frameRate)).intValue());
        // 设置音频通道数，与视频源的通道数相等
        recorder.setAudioChannels(grabber.getAudioChannels());
        //设置音频压缩方式
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);

        LOGGER.info("VideoBitrate = {}", grabber.getVideoBitrate());
        recorder.setVideoBitrate(grabber.getVideoBitrate());
        recorder.setSampleFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setSampleRate(grabber.getSampleRate());
        // 降低启动时的延时，参考
        // https://trac.ffmpeg.org/wiki/StreamingGuide)
        recorder.setVideoOption("tune", "zerolatency");
        // ultrafast对CPU消耗最低
        recorder.setVideoOption("preset", "ultrafast");
        // Constant Rate Factor (see: https://trac.ffmpeg.org/wiki/Encode/H.264)
        recorder.setVideoOption("crf", "28");
        recorder.setVideoOption("threads", "4");

        LOGGER.info("开始初始化帧抓取器");

        // 初始化帧录制器，例如数据结构（音频流、视频流指针，编码器），
        // 调用av_guess_format方法，确定视频输出时的封装方式，
        // 媒体上下文对象的内存分配，
        // 编码器的各项参数设置
        recorder.start();

        LOGGER.info("帧录制初始化完成，耗时[{}]毫秒", System.currentTimeMillis() - startTime);

        Frame frame;

        startTime = System.currentTimeMillis();

        LOGGER.info("开始推流");

        long videots;

        int videoFrameNum = 0;
        int audioFrameNum = 0;
        int dataFrameNum = 0;
        // 假设一秒钟15帧，那么两帧间隔就是(1000/15)毫秒
        double interVal = 1000 / frameRate;
        // 发送完一帧后sleep的时间，不能完全等于(1000/frameRate)，不然会卡顿，
        interVal /= 8;
        LOGGER.info("两帧间隔时间:{}毫秒", interVal);
        // 持续从视频源取帧
        while (null != (frame = grabber.grab())) {
            videots = 1000 * (System.currentTimeMillis() - startTime);
            // 时间戳
            recorder.setTimestamp(videots);
            // 有图像，就把视频帧加一
            if (null != frame.image) {
                videoFrameNum++;
            }

            // 有声音，就把音频帧加一
            if (null != frame.samples) {
                audioFrameNum++;
            }

            // 有数据，就把数据帧加一
            if (null != frame.data) {
                dataFrameNum++;
            }

            // 取出的每一帧，都推送到SRS
            recorder.record(frame);

            // 停顿一下再推送
            Thread.sleep((long) interVal);
        }

        LOGGER.info("推送完成，视频帧[{}]，音频帧[{}]，数据帧[{}]，耗时[{}]秒",
                videoFrameNum,
                audioFrameNum,
                dataFrameNum,
                (System.currentTimeMillis() - startTime) / 1000);

        // 关闭帧录制器
        recorder.close();
        // 关闭帧抓取器
        grabber.close();
    }

    public static void main(String[] args) throws Exception {
        grabAndPush(MP4_FILE_PATH, SRS_PUSH_ADDRESS);
    }
}
