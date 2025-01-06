package com.example.design.pattern.composite_pattern.test1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/5 16:00
 */
public class Folder extends AbstractFile {
    /**
     * 定义集合fileList，用于存储AbstractFile类型的成员
     */
    private List<AbstractFile> fileList = new ArrayList<>();
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        fileList.add(file);
    }

    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    public AbstractFile getChild(int i) {
        return fileList.get(i);
    }

    public void killVirus() {
        System.out.println("****对文件夹'" + name + "'进行杀毒");
        //递归调用成员构件的killVirus()方法
        for (AbstractFile obj : fileList) {
            obj.killVirus();
        }
    }
}
