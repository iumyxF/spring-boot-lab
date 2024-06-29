package com.example.files.service.upload.uploader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/6/27 14:27
 */
public class UploaderRepository {

    private final static List<Uploader> uploaderList = new ArrayList<>();

    static {
        uploaderList.add(new AliUploader());
        uploaderList.add(new TencentUploader());
        uploaderList.add(new BaiduUploader());
    }

    public static List<Uploader> list() {
        return uploaderList;
    }
}
