package com.example.netty.mediaclient.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fzy
 * @description: json文本流媒体数据
 * @date 2023/3/17 16:46
 */
@Data
public class MediaJsonData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String head;

    private Map<String, Object> cmd = new HashMap<>();

    private Map<String, Object> device = new HashMap<>();

    private Map<String, Object> args = new HashMap<>();

}
