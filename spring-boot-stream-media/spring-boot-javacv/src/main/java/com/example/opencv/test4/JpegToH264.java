package com.example.opencv.test4;

import cn.hutool.core.io.IoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.SeekableByteArrayOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JpegToH264 {

    public static void main(String[] args) throws IOException {
        //test
        //String source = "F:\\testData\\other\\test_image.jpeg";
        //String target = "F:\\testData\\other\\test_image_out.mp4";
        //jpegToH264(source, target);

        //test
        //testByte();

        //byte[] bytes = IoUtil.readBytes(Files.newInputStream(Paths.get("F:\\testData\\other\\test_image.jpeg")));
        //byte[] read = readWithByteArray(bytes);


        testByteNonFile();
    }

    public static void testByte() throws IOException {
        byte[] bytes = IoUtil.readBytes(Files.newInputStream(Paths.get("F:\\testData\\other\\test_image.jpeg")));
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;
        ByteArrayOutputStream outputStream;
        SeekableByteArrayOutputStream sbaos = new SeekableByteArrayOutputStream();
        //FileOutputStream fileOutputStream = new FileOutputStream("F:\\testData\\other\\test_image_out.mp4");
        try {
            grabber = new FFmpegFrameGrabber(new ByteArrayInputStream(bytes));
            grabber.start();

            outputStream = new ByteArrayOutputStream();
            //ok
            //recorder = new FFmpegFrameRecorder("F:\\testData\\other\\test_image_out.mp4", grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            //ok
            //recorder = new FFmpegFrameRecorder(new File("F:\\testData\\other\\test_image_out.mp4"), grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            //fail
            recorder = new FFmpegFrameRecorder(sbaos, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());

            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.setFrameRate(grabber.getFrameRate());

            recorder.start();

            Frame frame = grabber.grab();
            recorder.record(frame);

            recorder.stop();
            grabber.stop();

            IoUtil.write(Files.newOutputStream(Paths.get("F:\\testData\\other\\test_image_out.mp4")), true, sbaos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != grabber) {
                try {
                    grabber.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != recorder) {
                try {
                    recorder.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void testByteNonFile() throws IOException {
        byte[] bytes = IoUtil.readBytes(Files.newInputStream(Paths.get("F:\\testData\\other\\test_image.jpeg")));
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;
        SeekableByteArrayOutputStream sbaos = new SeekableByteArrayOutputStream();
        try {
            grabber = new FFmpegFrameGrabber(new ByteArrayInputStream(bytes));
            grabber.start();
            recorder = new FFmpegFrameRecorder(sbaos, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.start();

            Frame frame = grabber.grab();
            recorder.record(frame);
            recorder.stop();
            grabber.stop();
            IoUtil.write(Files.newOutputStream(Paths.get("F:\\testData\\other\\test_image_out.mp4")), true, sbaos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != grabber) {
                try {
                    grabber.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != recorder) {
                try {
                    recorder.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public static void jpegToH264(String jpegFile, String mp4File) {
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;
        try {
            grabber = new FFmpegFrameGrabber(jpegFile);
            grabber.start();
            recorder = new FFmpegFrameRecorder(mp4File, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.setFrameRate(grabber.getFrameRate());

            recorder.start();

            Frame frame = grabber.grab();
            recorder.record(frame);

            recorder.stop();
            grabber.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != grabber) {
                try {
                    grabber.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != recorder) {
                try {
                    recorder.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void createJpegFile(byte[] bytes) {
        try {
            String fileName = System.currentTimeMillis() + ".jpeg";
            IoUtil.write(new FileOutputStream("F:\\testData\\test\\" + fileName), true, bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void createH264File(byte[] bytes) {
        try {
            String fileName = System.currentTimeMillis() + ".264";
            IoUtil.write(new FileOutputStream("F:\\testData\\test\\" + fileName), true, bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用netty的ByteBuf 对流进行处理和包装
     *
     * @param buf 输入buffer
     * @return
     */
    public static ByteBuf jpegToH2645(ByteBuf buf) {
        avutil.av_log_set_level(avutil.AV_LOG_QUIET);
        FFmpegFrameGrabber grabber = null;
        FFmpegFrameRecorder recorder = null;
        SeekableByteArrayOutputStream sbaos = new SeekableByteArrayOutputStream();
        try {
            grabber = new FFmpegFrameGrabber(new ByteArrayInputStream(ByteBufUtil.getBytes(buf)));
            grabber.setFormat("mjpeg");
            grabber.start();

            recorder = new FFmpegFrameRecorder(sbaos, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("H264");
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.start();

            Frame frame = grabber.grab();
            recorder.record(frame);

            recorder.stop();
            grabber.stop();

            return Unpooled.wrappedBuffer(sbaos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != grabber) {
                try {
                    grabber.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != recorder) {
                try {
                    recorder.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

