package com.example.rtmp.entities.po;

import lombok.Data;

/**
 * @author fzy
 * @description:
 * @date 2024/11/19 17:10
 */
@Data
public class SyncPo {

    private String syncKey;

    private String publisher;

    private Integer syncType;

    private String rtmpUrl;

    // 画面参数等 略
}
