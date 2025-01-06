package com.example.design.bridge_pattern.test1;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/5 14:34
 */
public abstract class Image {

    protected ImageImp imp;

    public void setImageImp(ImageImp imp) {
        this.imp = imp;
    }

    public abstract void parseFile(String fileName);
}
