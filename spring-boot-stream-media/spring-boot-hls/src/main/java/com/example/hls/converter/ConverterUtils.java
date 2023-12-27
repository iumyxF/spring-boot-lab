package com.example.hls.converter;

import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import java.io.File;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/25 15:02
 */
public class ConverterUtils {


    /**
     * 将任意文件转换成HLS格式，视频编码H264，音频编码AAC
     *
     * @param file
     * @throws Exception
     */
    public void anyConvertHls(File file) throws Exception {
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        String tsName = file.getParent() + "\\" + fileName + "_%03d.ts";
        String inputfile = file.getPath();
        String outputfile = file.getParent() + "\\" + fileName + ".m3u8";


        if (outputfile.lastIndexOf('.') < 0) {
            throw new Exception("Error! Output file format undetected!");
        }
        FFmpegLogCallback.set();
        Frame frame;
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputfile);
        FFmpegFrameRecorder recorder = null;

        try {
            System.out.println("开始初始化帧抓取器");

            // 初始化帧抓取器，例如数据结构（时间戳、编码器上下文、帧对象等），
            // 如果入参等于true，还会调用avformat_find_stream_info方法获取流的信息，放入AVFormatContext类型的成员变量oc中
            grabber.start(true);

            System.out.println("帧抓取器初始化完成");

            // grabber.start方法中，初始化的解码器信息存在放在grabber的成员变量oc中
            AVFormatContext avformatcontext = grabber.getFormatContext();

            // 文件内有几个媒体流（一般是视频流+音频流）
            int streamNum = avformatcontext.nb_streams();

            // 没有媒体流就不用继续了
            if (streamNum < 1) {
                System.out.println("文件内不存在媒体流");
                throw new Exception("Error! There is no media stream in the file!");
            }

            // 取得视频的帧率
            double framerate = grabber.getVideoFrameRate();

            System.out.printf("视频帧率[%f]，视频时长[%d]秒，媒体流数量[%d]\r\n", framerate, avformatcontext.duration() / 1000000,
                    avformatcontext.nb_streams());

            // 遍历每一个流，检查其类型
            for (int i = 0; i < streamNum; i++) {
                AVStream avstream = avformatcontext.streams(i);
                AVCodecParameters avcodecparameters = avstream.codecpar();
                System.out.printf("流的索引[%d]，编码器类型[%d]，编码器ID[%d]\r\n", i, avcodecparameters.codec_type(),
                        avcodecparameters.codec_id());
            }

            // 视频宽度
            int frameWidth = grabber.getImageWidth();
            // 视频高度
            int frameHeight = grabber.getImageHeight();
            // 音频通道数量
            int audiochannels = grabber.getAudioChannels();

            System.out.printf("视频宽度[%d]，视频高度[%d]，音频通道数[%d]\r\n", frameWidth, frameHeight, audiochannels);

            recorder = new FFmpegFrameRecorder(outputfile, frameWidth, frameHeight, audiochannels);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);

            recorder.setFormat("hls");
            // 设置每个切片的时长为4秒
            recorder.setOption("hls_time", "4");
            // 设置切片列表的大小为0，表示不限制
            recorder.setOption("hls_list_size", "0");
            // 设置切片文件的命名格式，使用序号作为后缀
            recorder.setOption("hls_segment_filename", tsName);

            // 使用原始视频的码率，若需要则自行修改码率
            recorder.setVideoBitrate(grabber.getVideoBitrate());

            // 一秒内的帧数，帧率
            recorder.setFrameRate(framerate);

            // 两个关键帧之间的帧数
            recorder.setGopSize((int) framerate);

            // 设置音频通道数，与视频源的通道数相等
            recorder.setAudioChannels(grabber.getAudioChannels());

            recorder.start();

            int videoframenum = 0;
            int audioframenum = 0;
            int dataframenum = 0;

            // 持续从视频源取帧
            while (null != (frame = grabber.grab())) {
                // 有图像，就把视频帧加一
                if (null != frame.image) {
                    videoframenum++;
                    // 取出的每一帧，都保存到视频
                    recorder.record(frame);
                }

                // 有声音，就把音频帧加一
                if (null != frame.samples) {
                    audioframenum++;
                    // 取出的每一帧，都保存到视频
                    recorder.record(frame);
                }

                // 有数据，就把数据帧加一
                if (null != frame.data) {
                    dataframenum++;
                }
            }
            System.out.printf("转码完成，视频帧[%d]，音频帧[%d]，数据帧[%d]\r\n", videoframenum, audioframenum, dataframenum);

        } catch (Exception e) {
             e.printStackTrace();
            throw e;
        } finally {
            if (recorder != null) {
                try {
                    recorder.close();
                } catch (Exception e) {
                     System.out.println("recorder.close异常" + e);
                    throw e;
                }
            }

            try {
                grabber.close();
            } catch (FrameGrabber.Exception e) {
                 System.out.println("frameGrabber.close异常" + e);
                throw e;
            }
        }
    }
}
