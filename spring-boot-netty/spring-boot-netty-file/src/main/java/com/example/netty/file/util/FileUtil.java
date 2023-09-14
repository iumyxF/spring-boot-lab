package com.example.netty.file.util;


import com.example.netty.file.domain.Constants;
import com.example.netty.file.domain.FileBurstData;
import com.example.netty.file.domain.FileBurstInstruct;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description: 文件处理工具类
 * @Date 2023/3/11 11:00
 * @author iumyxF
 */
public class FileUtil {

    /**
     * 1kb常量
     */
    private final static int byteSize = 1024;

    /**
     * 读取容量
     */
    private final static int size = 100;

    /**
     * 读取文件
     *
     * @param fileUrl      文件url
     * @param readPosition 读取开始位置
     * @return 文件分片数据块
     * @throws IOException IO异常
     */
    public static FileBurstData readFile(String fileUrl, Integer readPosition) throws IOException {
        File file = new File(fileUrl);
        //r: 只读模式 rw:读写模式
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        //游标移动到开始位置
        randomAccessFile.seek(readPosition);
        //每次读取100Kb
        byte[] bytes = new byte[byteSize * size];
        int readSize = randomAccessFile.read(bytes);
        if (readSize <= 0) {
            //可读内容为-1代表读取完毕
            randomAccessFile.close();
            //Constants.FileStatus ｛0开始、1中间、2结尾、3完成｝
            return new FileBurstData(Constants.FileStatus.COMPLETE);
        }
        //创建一个分块文件
        FileBurstData fileInfo = new FileBurstData();
        fileInfo.setFileUrl(fileUrl);
        fileInfo.setFileName(file.getName());
        fileInfo.setBeginPos(readPosition);
        fileInfo.setEndPos(readPosition + readSize);
        //不足1024需要拷贝去掉空字节
        if (readSize < byteSize * size) {
            //代表本次读取的是最后一个分块文件
            byte[] copy = new byte[readSize];
            System.arraycopy(bytes, 0, copy, 0, readSize);
            fileInfo.setBytes(copy);
            fileInfo.setStatus(Constants.FileStatus.END);
        } else {
            fileInfo.setBytes(bytes);
            fileInfo.setStatus(Constants.FileStatus.CENTER);
        }
        randomAccessFile.close();
        return fileInfo;
    }

    /**
     * 写文件
     *
     * @param baseUrl       文件url
     * @param fileBurstData 文件分片数据块
     * @return FileBurstInstruct 文件分片指令
     * @throws IOException IO异常
     */
    public static FileBurstInstruct writeFile(String baseUrl, FileBurstData fileBurstData) throws IOException {
        //如果该分块文件状态为COMPLETE 代表文件已经写入完毕
        if (Constants.FileStatus.COMPLETE == fileBurstData.getStatus()) {
            //Constants.FileStatus ｛0开始、1中间、2结尾、3完成｝
            return new FileBurstInstruct(Constants.FileStatus.COMPLETE);
        }

        File file = new File(baseUrl + "/" + fileBurstData.getFileName());
        //r: 只读模式 rw:读写模式
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        //移动文件记录指针的位置
        randomAccessFile.seek(fileBurstData.getBeginPos());
        //调用了seek（start）方法，是指把文件的记录指针定位到start字节的位置。也就是说程序将从start字节开始写数据
        randomAccessFile.write(fileBurstData.getBytes());
        randomAccessFile.close();
        //如果该分块文件状态为END 写入一次后代表整个文件已经写入完毕
        if (Constants.FileStatus.END == fileBurstData.getStatus()) {
            //Constants.FileStatus ｛0开始、1中间、2结尾、3完成｝
            return new FileBurstInstruct(Constants.FileStatus.COMPLETE);
        }
        //文件分片传输指令
        FileBurstInstruct fileBurstInstruct = new FileBurstInstruct();
        //Constants.FileStatus ｛0开始、1中间、2结尾、3完成｝
        fileBurstInstruct.setStatus(Constants.FileStatus.CENTER);
        //客户端文件URL
        fileBurstInstruct.setClientFileUrl(fileBurstData.getFileUrl());
        //读取位置
        fileBurstInstruct.setReadPosition(fileBurstData.getEndPos() + 1);
        return fileBurstInstruct;
    }

}
