package com.example.design.proxy_pattern.demo1;

/**
 * @author iumyxF
 * @description: 视频文件实体
 * @date 2023/12/5 10:47
 */
public class Video {

    public String id;

    public String title;

    public String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }
}
