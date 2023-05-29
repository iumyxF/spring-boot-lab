package com.example.video.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fzy
 * @description: 视频分辨率转换器
 * @date 2023/5/29 14:31
 */
public class VideoConverter {

    /**
     * ffmpeg程序的路径
     */
    public static final String FFMPEG_PATH = "D:\\ffmpeg\\ffmpeg-2023-05-25-git-944243477b-full_build\\bin\\ffmpeg.exe";

    /**
     * 源视频文件的路径
     */
    public static final String SOURCE_VIDEO_PATH = "C:\\temp\\016.mp4";

    /**
     * 目标视频文件的路径
     */
    public static final String TARGET_VIDEO_PATH = "C:\\temp\\016_1920_1080_2.mp4";

    /**
     * 目标视频文件的分辨率1
     * 1k 1920:1080
     * 2k 2560:1440
     * 4k 3840:2160
     */
    public static final String TARGET_RESOLUTION = "1920:1080";

    /**
     * 进度文件的路径
     */
    public static final String PROGRESS_FILE_PATH = "D:/temp/progress.txt";

    /**
     * 进度文件的编码
     */
    public static final String PROGRESS_FILE_ENCODING = "UTF-8";

    /**
     * 进度正则表达式
     */
    public static final Pattern PROGRESS_PATTERN = Pattern.compile("progress=(\\w+)");

    /**
     * D:\ffmpeg\ffmpeg-2023-05-25-git-944243477b-full_build\bin\ffmpeg.exe -i C:\temp\016.mp4 -vf scale=2560:1440 -progress D:/temp/progress.txt C:\temp\016_2k.mp4
     */
    public static void main(String[] args) {
        try {
            // 构建ffmpeg命令，添加-progress参数
            String command = FFMPEG_PATH + " -i " + SOURCE_VIDEO_PATH + " -vf scale=" + TARGET_RESOLUTION + " -progress " + PROGRESS_FILE_PATH + " " + TARGET_VIDEO_PATH;
            System.out.println("执行的cmd命令: " + command);
            // 执行命令并获取输出
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

            // 创建一个线程来读取进度文件并显示
            Thread progressThread = new Thread(() -> {
                try {
                    File progressFile = new File(PROGRESS_FILE_PATH);
                    while (true) {
                        if (progressFile.exists()) {
                            // 读取进度文件的最后一行
                            List<String> lines = FileUtils.readLines(progressFile, PROGRESS_FILE_ENCODING);
                            if (!lines.isEmpty()) {
                                String lastLine = lines.get(lines.size() - 1);
                                // 匹配进度信息
                                Matcher matcher = PROGRESS_PATTERN.matcher(lastLine);
                                if (matcher.find()) {
                                    String progress = matcher.group(1);
                                    if ("end".equals(progress)) {
                                        // 进度结束，退出循环
                                        System.out.println("转换完成！");
                                        break;
                                    } else {
                                        // 进度继续，显示百分比
                                        System.out.println("转换进度：" + progress + "%");
                                    }
                                }
                            }
                        }
                        // 每隔一秒读取一次
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // 启动线程
            progressThread.start();

            // 等待命令执行完成
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("视频转换成功！");
            } else {
                System.out.println("视频转换失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

