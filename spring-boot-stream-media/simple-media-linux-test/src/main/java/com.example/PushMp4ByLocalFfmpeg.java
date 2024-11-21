package com.example;

/**
 * @author iumyx
 * @description: 抓取本地MP4文件 推流到SRS服务器上，目前缺点：掉帧、画面模糊
 * @date 2023/9/27 16:08
 */

public class PushMp4ByLocalFfmpeg {

    /**
     * 视频地址
     */
    private static final String VIDEO_URL = "http://192.168.2.191/files/testData/movie-test.mp4";

    /**
     * SRS的推流地址
     */
    private static final String SRS_PUSH_ADDRESS = "rtmp://192.168.2.161/live/livestream/test";

    /**
     * 第一个参数 VIDEO_URL F:/testData/123.mp4
     * 第二个参数 SRS地址 rtmp://192.168.2.161/live/livestream/test2
     */
    private static final String PUSH_CMD = String.format("ffmpeg -re -i %s -c:v libx264 -preset ultrafast -b:v 500k -s 1280*768 -r 15 -f flv %s", VIDEO_URL, SRS_PUSH_ADDRESS);

    public static void main(String[] args) {
        System.out.println(PUSH_CMD);
        // 使用cmdUtil 运行命令
    }

}
