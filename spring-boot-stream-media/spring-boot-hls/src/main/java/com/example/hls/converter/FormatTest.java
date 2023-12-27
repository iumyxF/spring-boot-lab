package com.example.hls.converter;

import java.io.File;

/**
 * @author iumyxF
 * @description: 测试不同文件格式转换成HLS
 * @date 2023/12/21 10:11
 */
public class FormatTest {

    public static void main(String[] args) {
        //avi ac
        File aviFile = new File("F:\\testData\\mediaTest\\AVI\\AV36_1.AVI");
        //flv ac
        File flvFile = new File("F:\\testData\\mediaTest\\FLV\\flvVideo.flv");
        //mkv ac
        File mkvFile = new File("F:\\testData\\mediaTest\\MKV\\mkvVideo.mkv");
        //mov ac
        File movFile = new File("F:\\testData\\mediaTest\\MOV\\movVideo.mov");
        //mp4 ac
        File mp4File = new File("F:\\testData\\mediaTest\\MP4\\mp4Video.mp4");
        //wmv ac
        File wmvFile = new File("F:\\testData\\mediaTest\\WMV\\wmvVideo.wmv");
        convertHls(aviFile);
        //convertHls(flvFile);
        //convertHls(mkvFile);
        //convertHls(movFile);
        //convertHls(mp4File);
        //convertHls(wmvFile);
    }

    /**
     * ffmpeg -i input.mp4 -c:v copy -hls_time 2  -hls_segment_filename %d.ts -f hls output/playlist.m3u8
     * file.getPath() F:\testData\mediaTest\AVI\AV36_1.AVI
     * file.getParent() F:\testData\mediaTest\AVI
     * file.getName() AV36_1.AVI
     */
    public static void convertHls(File file) {
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        //tsName = F:\testData\mediaTest\AVI\AV36_1_%03d.ts
        String tsName = file.getParent() + "\\" + fileName + "_%03d.ts";
        //outputFile = F:\testData\mediaTest\AVI\AV36_1.m3u8
        String outputFile = file.getParent() + "\\" + fileName + ".m3u8";
        String cmd = String.format("ffmpeg -i %s " +
                //libx264是H264（规范）的实现
                //"-codec:v libx264 -codec:a aac " +
                "-codec:v copy -codec:a copy " +
                "-map 0 " +
                "-f hls " +
                "-hls_time 4 " +
                "-hls_list_size 0 " +
                "-hls_segment_filename %s %s", file.getPath(), tsName, outputFile);
        System.out.println(cmd);
        //try {
        //    if (SystemUtils.IS_OS_WINDOWS) {
        //        System.out.println("windows环境");
        //        ProcessBuilder builder = CmdUtil.exec(cmd);
        //        builder.start();
        //    } else if (SystemUtils.IS_OS_LINUX) {
        //        System.out.println("linux环境");
        //        ProcessBuilder builder = CmdUtil.exec("sh", "-c", cmd);
        //        builder.start();
        //    }
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
    }

}
