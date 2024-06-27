package com.example.files.controller;

import com.example.files.service.upload.PicUploadService;
import com.example.files.utils.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/27 9:28
 */
@RestController
public class PictureController {

    @Resource
    private PicUploadService picUploadService;

    /**
     * 图片上传
     * 1. 保证可用性，如果某个图床服务上传失败，尝试另一个图床服务
     * <p>
     * 扩展
     * 根据不同接口限制不同文件的大小、创建不同的临时文件
     *
     * @param file
     * @return
     */
    @PostMapping("/picture")
    public String uploadPicture(@RequestParam MultipartFile file) {
        // 文件大小判断...略
        File tempPicFile = FileUtils.creatTempPicFile(file);
        return picUploadService.upload(tempPicFile);
    }
}
