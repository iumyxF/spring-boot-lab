package com.example.design.pattern.adapter_pattern.test01.adapters;

import com.example.design.pattern.adapter_pattern.test01.round.RoundPeg;
import com.example.design.pattern.adapter_pattern.test01.square.SquarePeg;

/**
 * @author iumyxF
 * @description: 方钉到圆孔的适配器
 * @date 2023/9/11 13:56
 */
public class SquarePegAdapter extends RoundPeg {

    private SquarePeg peg;

    public SquarePegAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        double result;
        // 计算正方形外接圆半径 （根号2/2 * 宽）
        result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
        return result;
    }
}
