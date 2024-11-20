package com.example.rtmp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 17:16
 */
@Getter
@Configuration
public class MediaConfig {

    @Value("${media.srs.url}")
    private String srsUrl;

    public String getRtmpUrl() {
        return srsUrl + "/live/livestream/";
    }
}
