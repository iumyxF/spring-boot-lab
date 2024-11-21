package com.example.rtsp.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author fzy
 * @description:
 * @date 2024/11/20 10:01
 */
@Component
public class RtspClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 无法使用
        // 连接到RTSP流并开始读取数据
        //FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("rtsp://127.0.0.1:8554/mylive");
        //grabber.start();
        //Java2DFrameConverter converter = new Java2DFrameConverter();
        //while (true) {
        //    // 读取一帧数据
        //    Frame frame = grabber.grab();
        //
        //    if (frame != null) {
        //        // 将帧转换为BufferedImage
        //        BufferedImage image = converter.getBufferedImage(frame);
        //        if (image != null){
        //            // 将BufferedImage转换为字节数组
        //            byte[] imageData = bufferedImageToBytes(image);
        //            // 将数据发送到服务器
        //            ctx.writeAndFlush(imageData);
        //        }
        //    }
        //}
    }

    private byte[] bufferedImageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

