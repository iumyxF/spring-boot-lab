package com.example.media.common.bo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author feng
 * @description: json格式数据
 * 例如：{"head":"test","device":{"id":"8888"},"cmd":{"cmd":"reg","response":0},"args":{"group_name":"239.255.255.19:2837","sender":true}}
 * @date 2024/11/12 20:40
 */
@Data
public class MediaJsonData {

    private String head;

    private Map<String, Object> device = new HashMap<>();

    private Map<String, Object> cmd = new HashMap<>();

    private Map<String, Object> args = new HashMap<>();

    /**
     * {@link com.example.media.common.enums.JsonMessageType}
     */
    private String type;

}
