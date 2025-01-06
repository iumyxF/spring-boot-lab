package com.example.design.bridge_pattern.test1.image;

import com.example.design.bridge_pattern.test1.Image;
import com.example.design.bridge_pattern.test1.Matrix;

/**
 * @author iumyxF
 * @description: JPG格式图像：扩充抽象类
 * @date 2024/1/5 14:36
 */
public class JPGImage extends Image {
    public void parseFile(String fileName) {
        //模拟解析JPG文件并获得一个像素矩阵对象m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "，格式为JPG。");
    }
}
