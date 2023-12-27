package com.example.hls.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoCodecTest {

    /**
     * 22 - 5 - 2 ，15d 高 12 兵
     *
     * @param args
     */
    public static void main(String[] args) {
        // 指定视频文件的路径
        String videoPath = "F:\\testData\\mediaTest\\Big\\Pandas.mp4";
        // 构造ffmpeg命令，-i表示输入文件
        String cmd = "ffmpeg -i " + videoPath;
        try {
            // 使用ProcessBuilder类来执行ffmpeg命令
            ProcessBuilder pb = new ProcessBuilder(cmd.split(" "));
            // 启动进程
            Process process = pb.start();
            // 获取错误流，这里ffmpeg会输出视频的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            // 定义正则表达式，用于匹配视频编码格式和音频编码格式
            Pattern videoPattern = Pattern.compile("Video: (\\w+)");
            Pattern audioPattern = Pattern.compile("Audio: (\\w+)");
            // 定义变量，用于存储编码格式
            String videoCodec = null;
            String audioCodec = null;
            // 逐行读取错误流的输出
            String line;
            while ((line = br.readLine()) != null) {
                // 如果还没有找到视频编码格式，就尝试匹配
                if (videoCodec == null) {
                    Matcher videoMatcher = videoPattern.matcher(line);
                    if (videoMatcher.find()) {
                        // 如果匹配成功，就获取第一个分组的内容，即视频编码格式
                        videoCodec = videoMatcher.group(1);
                    }
                }
                // 如果还没有找到音频编码格式，就尝试匹配
                if (audioCodec == null) {
                    Matcher audioMatcher = audioPattern.matcher(line);
                    if (audioMatcher.find()) {
                        // 如果匹配成功，就获取第一个分组的内容，即音频编码格式
                        audioCodec = audioMatcher.group(1);
                    }
                }
                // 如果都找到了，就不用继续读取了
                if (videoCodec != null && audioCodec != null) {
                    break;
                }
            }
            // 关闭错误流
            br.close();
            // 等待进程结束
            process.waitFor();
            // 输出结果到控制台
            System.out.println("视频编码格式: " + videoCodec);
            System.out.println("音频编码格式: " + audioCodec);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

