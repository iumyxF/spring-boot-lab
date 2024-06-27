package com.example.files.service.upload.uploader;

import com.example.files.model.dto.UploadCredential;
import com.example.files.model.dto.UploadResponse;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 14:17
 */
public class AliUploader extends AbstractUploader {
    @Override
    protected UploadCredential getCredential() {
        // 构建自己的凭证信息 可从配置文件或配置类、系统环境变量等途径获取
        UploadCredential credential = new UploadCredential();
        credential.setAccessKey("ali-ak");
        credential.setSecretAccessKey("ali-sk");
        credential.setBucket("xxx");
        credential.setRegion("xxx");
        return credential;
    }

    @Override
    protected UploadResponse upload(UploadCredential credential) {
        // 方式一:使用api接口发送http请求
        // 方式二:使用官方SDK实现
        return null;
    }
}
