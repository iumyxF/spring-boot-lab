package com.example.media.common;

import lombok.Data;

/**
 * @author feng
 * @description:
 * @date 2024/11/12 20:39
 */
@Data
public class MediaBinaryData {

    private byte head;
    private int length;

    private byte[] data;

}
