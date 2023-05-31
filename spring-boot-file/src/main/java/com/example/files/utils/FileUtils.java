package com.example.files.utils;

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
}
