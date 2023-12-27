package com.example.hls.converter;

import org.bytedeco.javacpp.Loader;

import java.io.IOException;
import java.util.Properties;

/**
 * @author iumyxF
 * @description:
 * @date 2023/12/25 15:19
 */
public class Temp3 {

    /**
     * ffmpeg -i F:\testData\mediaTest\AVI\AV36_1.AVI
     * -codec:v copy
     * -codec:a copy
     * -map 0
     * -f hls
     * -hls_time 4
     * -hls_list_size 0
     * -hls_segment_filename F:\testData\mediaTest\AVI\AV36_1_%03d.ts
     * F:\testData\mediaTest\AVI\AV36_1.m3u8
     */
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() throws IOException, InterruptedException {
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        String from = "F:\\testData\\mediaTest\\AVI\\AV36_1.AVI";
        String to = "F:\\testData\\mediaTest\\AVI\\AV36_1.m3u8";
        ProcessBuilder pb = new ProcessBuilder(ffmpeg,
                "-i", from,
                "-codec:v", "copy",
                "-codec:a", "copy",
                "-map", "0",
                "-f", "hls",
                "-hls_time", "4",
                "-hls_list_size", "0",
                "-hls_segment_filename", "F:\\testData\\mediaTest\\AVI\\AV36_1_%03d.ts",
                to);
        Process process = pb.inheritIO().start();
        //正常退出i=0,其他不正常i=1
        int i = process.waitFor();
        System.out.println(i);

        System.out.println();
    }

    public static void test2() throws IOException, InterruptedException {
        //String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        String from = "F:\\testData\\mediaTest\\AVI\\AV36_1.AVI";
        String to = "F:\\testData\\mediaTest\\AVI\\AV36_1.m3u8";
        ProcessBuilder pb = new ProcessBuilder("ffmpeg",
                "-i", from,
                "-codec:v", "libx264",
                "-codec:a", "aac",
                "-map", "0",
                "-f", "hls",
                "-hls_time", "4",
                "-hls_list_size", "0",
                "-hls_segment_filename", "F:\\testData\\mediaTest\\AVI\\AV36_1_%03d.ts",
                to);
        Process process = pb.inheritIO().start();
        //正常退出i=0,其他不正常i=1
        int i = process.waitFor();
        System.out.println(i);
    }

    public static void test3(){
        Properties properties = System.getProperties();
        String ffmpeg = System.getProperty("ffmpeg");
        System.out.println(ffmpeg);
    }
}
