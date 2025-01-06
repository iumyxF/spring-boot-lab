package com.example.design.composite_pattern.test1;

/**
 * @author iumyxF
 * @description: 文本类文件 叶子构件
 * @date 2024/1/5 16:15
 */
public class TextFile extends AbstractFile {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    public void killVirus() {
        //模拟杀毒
        System.out.println("----对文本类文件'" + name + "'进行杀毒");
    }
}