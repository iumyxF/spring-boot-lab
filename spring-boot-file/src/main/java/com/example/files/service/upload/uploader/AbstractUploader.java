package com.example.files.service.upload.uploader;

import com.example.files.model.dto.UploadCredential;
import com.example.files.model.dto.UploadResponse;

import java.io.File;

/**
 * @author iumyx
 * @description: 上传模板
 * @date 2024/6/27 11:14
 */
public abstract class AbstractUploader implements Uploader {

    @Override
    public String doUpload(File file) {
        UploadCredential credential = getCredential();
        UploadResponse response = upload(credential);
        return parseResponse(response);
    }

    protected abstract UploadCredential getCredential();

    protected abstract UploadResponse upload(UploadCredential credential);

    protected String parseResponse(UploadResponse response) {
        return response.getMsg();
    }
}
