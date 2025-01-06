package com.example.design.pattern.composite_pattern.test1;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/5 15:58
 */
public abstract class AbstractFile {
    public abstract void add(AbstractFile file);

    public abstract void remove(AbstractFile file);

    public abstract AbstractFile getChild(int i);

    public abstract void killVirus();
}
