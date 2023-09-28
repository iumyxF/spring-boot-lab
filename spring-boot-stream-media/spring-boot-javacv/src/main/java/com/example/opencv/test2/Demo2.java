package com.example.opencv.test2;

/**
 * 测试类
 *
 * @author alderaan
 * @version 创建时间：2022年5月10日 下午3:47:49
 */
public class Demo2 {
    public static void main(String[] args) {
        try {
            // videoConvert函数，根据outputfile的格式后缀设置转码后的视频格式，推荐使用mp4格式后缀
            VideoUtil.videoConvert("F:\\testData\\test_image.jpg", "F:\\testData\\result.mp4");
        } catch (java.lang.Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("执行完毕！");
    }
}


