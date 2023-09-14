package com.example.netty.file.domain;

/**
 * @description: 文件传输信息
 * @Date 2023/3/11 11:00
 * @author iumyxF
 */
public class FileDescInfo {

    private String fileUrl;

    private String fileName;

    private Long fileSize;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
