package com.example.files.utils;

import cn.hutool.core.util.IdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type File utils.
 *
 * @author iumyxF
 */
public class FileUtils {

    /**
     * Save file to local.
     *
     * @param content  the content
     * @param filePath the file path
     */
    public static void saveFileToLocal(String content, String filePath) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete file.
     *
     * @param filePath the file path
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * Modify file content.
     *
     * @param filePath   the file path
     * @param newContent the new content
     */
    public static void modifyFileContent(String filePath, String newContent) {
        saveFileToLocal(newContent, filePath);
    }

    /**
     * Rename file.
     *
     * @param oldFilePath the old file path
     * @param newFilePath the new file path
     */
    public static void renameFile(String oldFilePath, String newFilePath) {
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        if (oldFile.exists() && oldFile.isFile()) {
            oldFile.renameTo(newFile);
        }
    }

    /**
     * List all files.
     *
     * @param dirPath the dir path
     */
    public static void listAllFiles(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getAbsolutePath());
                    } else {
                        listAllFiles(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * Read file content string.
     *
     * @param filePath the file path
     * @return the string
     */
    public static String readFileContent(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Copy file.
     *
     * @param sourceFilePath the source file path
     * @param targetFilePath the target file path
     */
    public static void copyFile(String sourceFilePath, String targetFilePath) {
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.copy(sourcePath, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String[] IMAGE_SUFFIX = new String[]{"bmp", "jpg", "jpeg", "png", "gif"};

    /**
     * 创建临时文件
     *
     * @param multipartFile 请求文件
     * @return File
     */
    public static File creatTempPicFile(MultipartFile multipartFile) {
        // 重新命名文件 ${原文件名}_${随机数}.${文件类型}
        String originalFilename = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            // 这里应该抛异常
            return null;
        }
        int index = originalFilename.lastIndexOf(".");
        if (-1 == index) {
            return null;
        }
        String suffix = originalFilename.substring(index);
        for (String is : IMAGE_SUFFIX) {
            if (!StringUtils.equalsIgnoreCase(is, suffix)) {
                return null;
            }
        }
        String tempName = originalFilename.substring(0, index) + "_" + IdUtil.nanoId(5) + suffix;
        return new File(tempName);
    }
}