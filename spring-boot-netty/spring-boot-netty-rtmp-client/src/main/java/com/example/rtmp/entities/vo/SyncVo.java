package com.example.rtmp.entities.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author fzy
 * @description: 同屏对象
 * @date 2024/11/19 16:44
 */
@Data
@Builder
public class SyncVo {

    /**
     * 同屏标识
     */
    private String syncKey;

    /**
     * rtmp地址
     */
    private String rtmpUrl;
}
