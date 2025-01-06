package com.example.design.adapter_pattern.test01.round;

/**
 * @author iumyxF
 * @description: 圆孔
 * @date 2023/9/11 13:55
 */
public class RoundHole {

    private double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        boolean result;
        result = (this.getRadius() >= peg.getRadius());
        return result;
    }
}
