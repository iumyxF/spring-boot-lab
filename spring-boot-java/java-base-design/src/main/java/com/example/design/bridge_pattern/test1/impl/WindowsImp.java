package com.example.design.bridge_pattern.test1.impl;

import com.example.design.bridge_pattern.test1.ImageImp;
import com.example.design.bridge_pattern.test1.Matrix;

/**
 * @author iumyxF
 * @description: Windows操作系统实现类：具体实现类
 * @date 2024/1/5 14:34
 */
public class WindowsImp implements ImageImp {
    @Override
    public void doPaint(Matrix m) {
        System.out.print("在Windows操作系统中显示图像：");
    }
}
