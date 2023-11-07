package com.exmaple.demo;

import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.io.ByteBufferSeekableByteChannel;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.common.model.Rational;
import org.jcodec.scale.AWTUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author iumyxF
 * @description:
 * @date 2023/10/30 16:38
 */
public class Test01 {

    public static void main(String[] args) throws Exception {
        // 创建一个输出文件
        File output = new File("F:\\testData\\output.mp4");
        // 创建一个序列编码器，指定输出文件和帧率
        //ByteBufferSeekableByteChannel channel = new ByteBufferSeekableByteChannel(ByteBuffer.allocate(1024), 1024);
        //AWTSequenceEncoder encoder = new AWTSequenceEncoder(channel, Rational.R(25, 1));
        AWTSequenceEncoder encoder = new AWTSequenceEncoder(NIOUtils.writableChannel(output), Rational.R(25, 1));
        // 创建一个字节数组，存放JPEG格式的数据
        byte[] jpegBytes = readImage();
        // 将字节数组转换成BufferedImage对象
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(jpegBytes));
        // 使用编码器将BufferedImage对象编码成H264帧，并添加到输出文件中
        encoder.encodeImage(image);
        // 关闭编码器
        encoder.finish();
        //ByteBuffer buffer = channel.getContents();
        //System.out.println("H264版本");
        //System.out.println(Arrays.toString(buffer.array()));
    }

    public static byte[] readImage() throws Exception {
        //// 创建一个输入文件，这里假设是test.jpg，您可以根据您的实际情况修改
        //File input = new File("test.jpg");
        //// 使用JCodec的IOUtils类读取输入文件的字节数组
        //byte[] inputBytes = IOUtils.readFileToByteArray(input);
        //// 使用JCodec的NIOUtils类将字节数组转换成ByteBuffer对象
        //ByteBuffer buffer = NIOUtils.asByteBuffer(inputBytes);
        //// 使用JCodec的AWTUtil类将ByteBuffer对象转换成Picture对象
        //Picture picture = AWTUtil.fromBufferedImage(ImageIO.read(buffer));
        //// 使用JCodec的AWTUtil类将Picture对象转换成BufferedImage对象
        //BufferedImage image = AWTUtil.toBufferedImage(picture);
        //// 创建一个字节数组输出流
        //ByteArrayOutputStream output = new ByteArrayOutputStream();
        //// 使用ImageIO类将BufferedImage对象写入输出流，并指定JPEG格式
        //ImageIO.write(image, "jpg", output);
        //// 获取输出流中的字节数组
        //byte[] outputBytes = output.toByteArray();
        //// 打印输出字节数组的长度，以验证是否成功转换
        //System.out.println("Output byte array length: " + outputBytes.length);

        // 创建一个输入文件，这里假设是test.jpg，您可以根据您的实际情况修改
        File input = new File("F:\\testData\\test_image.jpg");
        // 使用ImageIO类读取输入文件的BufferedImage对象
        BufferedImage image = ImageIO.read(input);
        // 创建一个字节数组输出流
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        // 使用ImageIO类将BufferedImage对象写入输出流，并指定JPEG格式
        ImageIO.write(image, "jpg", output);
        // 获取输出流中的字节数组
        byte[] outputBytes = output.toByteArray();
        // 打印输出字节数组的长度，以验证是否成功转换
        System.out.println("初始版本");
        System.out.println("Output byte array length: " + Arrays.toString(outputBytes));

        return outputBytes;
    }

    public byte[] jpegToH264(byte[] jpegData) throws IOException {
        // 创建一个ByteArrayOutputStream，用于存储转换后的H264数据
        ByteBufferSeekableByteChannel buffer = ByteBufferSeekableByteChannel.readFromByteBuffer(ByteBuffer.wrap(jpegData));
        // 创建一个AWTSequenceEncoder，指定输出流和帧率
        AWTSequenceEncoder encoder = new AWTSequenceEncoder(buffer, Rational.R(30, 1));
        // 将JPEG数据转换为BufferedImage
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(jpegData));
        // 将BufferedImage转换为Picture
        Picture picture = AWTUtil.fromBufferedImage(image, ColorSpace.ANY);
        // 将Picture编码为H264帧，并写入输出流
        encoder.encodeNativeFrame(picture);
        // 结束编码并关闭输出流
        encoder.finish();
        // 将输出流的内容读取为byte[]
        byte[] h264Data = buffer.getContents().array();
        // 返回转换后的byte[]
        return h264Data;
    }


}
