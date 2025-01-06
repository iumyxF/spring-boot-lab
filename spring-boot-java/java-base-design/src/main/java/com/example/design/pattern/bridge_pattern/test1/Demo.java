package com.example.design.pattern.bridge_pattern.test1;

import com.example.design.pattern.bridge_pattern.test1.image.GIFImage;
import com.example.design.pattern.bridge_pattern.test1.impl.WindowsImp;

/**
 * @author iumyxF
 * @description:
 * @date 2024/1/5 14:24
 */
public class Demo {

    /*
     * 桥接模式(Bridge Pattern)：将抽象部分与它的实现部分分离，使它们都可以独立地变化。
     * 它是一种对象结构型模式，又称为柄体(Handle and Body)模式或接口(Interface)模式。
     *
     * 假如我们需要大中小3种型号的画笔，能够绘制12种不同的颜色，
     * 如果使用蜡笔，需要准备3×12 = 36支，
     * 但如果使用毛笔的话，只需要提供3种型号的毛笔，外加12个颜料盒即可，
     * 涉及到的对象个数仅为 3 + 12 = 15，远小于36，却能实现与36支蜡笔同样的功能。
     *
     * 在蜡笔中，颜色和型号两个不同的变化维度（即两个不同的变化原因）融合在一起，无论是对颜色进行扩展还是对型号进行扩展都势必会影响另一个维度；
     * 但在毛笔中，颜色和型号实现了分离，增加新的颜色或者型号对另一方都没有任何影响。
     */

    /**
     * 图片 - 格式扩展
     * 图片 - 不同平台的播放方式扩展
     */
    public static void main(String[] args) {
        //确定播放平台
        WindowsImp windowsImp = new WindowsImp();
        //确定图片格式
        GIFImage gifImage = new GIFImage();
        //装载并解析
        gifImage.setImageImp(windowsImp);
        gifImage.parseFile("test.gif");
    }
}
