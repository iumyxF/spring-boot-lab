package com.example.design.proxy_pattern.demo1;

import java.util.HashMap;

/**
 * @author iumyxF
 * @description: 远程服务接口
 * @date 2023/12/5 10:47
 */
public interface ThirdPartyYouTubeLib {
    /**
     * 获取热门视频
     */
    HashMap<String, Video> popularVideos();

    /**
     * 获取视频详情
     */
    Video getVideo(String videoId);
}
