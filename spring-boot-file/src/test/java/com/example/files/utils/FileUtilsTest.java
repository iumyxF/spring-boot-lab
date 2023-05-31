package com.example.files.utils;


import org.junit.jupiter.api.Test;

/**
 * @author fzy
 * @description: 文件操作工具类
 * @date 2023/5/30 9:21
 */
public class FileUtilsTest {

    @Test
    public void test() {
        // 1.文件保存到本地
        String content = "Hello, world!";
        String filePath = "f:\\test\\test1.txt";
        FileUtils.saveFileToLocal(content, filePath);
        System.out.println("文件保存成功");

        // 拷贝文件
        String newFilePath = "f:\\test\\test2.txt";
        FileUtils.copyFile(filePath, newFilePath);
        System.out.println("文件拷贝成功");


        // 3.修改文件的内容
        content = "Hello, Java!";
        FileUtils.modifyFileContent(content, newFilePath);
        System.out.println("文件修改成功");

        // 拷贝文件
        newFilePath = "f:\\test\\test3.txt";
        FileUtils.copyFile(filePath, newFilePath);
        System.out.println("文件拷贝成功");

        // 4.修改文件的名字
        FileUtils.renameFile(newFilePath, newFilePath);
        System.out.println("文件重命名成功");

        // 5.递归读取某个目录下所有文件
        String dirPath = "f:\\test";
        System.out.println("目录下所有文件如下：");
        FileUtils.listAllFiles(dirPath);

        // 6.读取某个文件的所有内容
        content = FileUtils.readFileContent(newFilePath);
        System.out.println("文件内容如下：");
        System.out.println(content);

        // 7.拷贝文件
        newFilePath = "f:\\test\\test5.txt";
        FileUtils.copyFile(filePath, newFilePath);
        System.out.println("文件拷贝成功");

        // 2.删除某个文件
        FileUtils.deleteFile(newFilePath);
        System.out.println("文件删除成功");
    }


    @Test
    public void saveFileToLocal() {
    }

    @Test
    public void deleteFile() {
    }

    @Test
    public void modifyFileContent() {
    }

    @Test
    public void renameFile() {
    }

    @Test
    public void listAllFiles() {
    }

    @Test
    public void readFileContent() {
    }

    @Test
    public void copyFile() {
    }
}