package com.example.design.pattern.bridge_pattern.test1.image;

import com.example.design.pattern.bridge_pattern.test1.Image;
import com.example.design.pattern.bridge_pattern.test1.Matrix;

/**
 * @author iumyxF
 * @description: PNG格式图像：扩充抽象类
 * @date 2024/1/5 14:36
 */
public class PNGImage extends Image {

    public void parseFile(String fileName) {
        //模拟解析Png文件并获得一个像素矩阵对象m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "，格式为PNG。");
    }
}
