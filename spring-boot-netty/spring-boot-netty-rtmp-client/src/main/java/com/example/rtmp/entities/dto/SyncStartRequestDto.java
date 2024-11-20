package com.example.rtmp.entities.dto;

import cn.hutool.core.util.StrUtil;
import com.example.rtmp.entities.enums.SyncType;
import lombok.Data;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 16:54
 */
@Data
public class SyncStartRequestDto {

    /**
     * 发布者主键
     */
    private String publisher;

    /**
     * 订阅者主键
     */
    private List<String> subscriber;

    /**
     * 同屏类型 （桌面同屏 = 1，编码器同屏 = 2）
     * {@link SyncType}
     */
    private Integer syncType;

    /*
      以下是非必填参数，用于控制画面 在编码器同屏时才有用
     */

    /**
     * 码率 bitrate=500 代表每秒传输数据量500K
     */
    private String bitrate;

    /**
     * 画面分辨率 默认 1280*768
     */
    private String resolution;

    /**
     * 帧率 默认 30
     */
    private String framerate;

    public void checkParams() {
        if (StrUtil.isBlank(publisher)) {
            throw new RuntimeException("publisher param error");
        }
        if (null == syncType || null == SyncType.getByType(syncType)) {
            throw new RuntimeException("syncType param error");
        }
    }
}
