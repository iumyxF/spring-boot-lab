package com.example.design.pattern.adapter_pattern.test01.square;

/**
 * @author iumyxF
 * @description: 方钉
 * @date 2023/9/11 13:56
 */
public class SquarePeg {

    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getSquare() {
        double result;
        result = Math.pow(this.width, 2);
        return result;
    }
}
