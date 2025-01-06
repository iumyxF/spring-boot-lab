package com.example.design.pattern.bridge_pattern.test1.impl;

import com.example.design.pattern.bridge_pattern.test1.ImageImp;
import com.example.design.pattern.bridge_pattern.test1.Matrix;

/**
 * @author iumyxF
 * @description: Linux操作系统实现类：具体实现类
 * @date 2024/1/5 14:34
 */
public class LinuxImp implements ImageImp {
    @Override
    public void doPaint(Matrix m) {
        System.out.print("在Linux操作系统中显示图像：");
    }
}
