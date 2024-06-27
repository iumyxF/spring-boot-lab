package com.example.files.service.upload.uploader;

import java.io.File;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 10:14
 */
public interface Uploader {

    String doUpload(File file);
}