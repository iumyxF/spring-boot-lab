package com.example.design.adapter_pattern.test01;

import com.example.design.adapter_pattern.test01.adapters.SquarePegAdapter;
import com.example.design.adapter_pattern.test01.round.RoundHole;
import com.example.design.adapter_pattern.test01.round.RoundPeg;
import com.example.design.adapter_pattern.test01.square.SquarePeg;

/**
 * @author iumyxF
 * @description:
 * @date 2023/9/11 13:57
 */
public class Demo {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            System.out.println("半径5mm的圆钉能装入半径5mm的圆孔");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);

        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("宽度2mm的方钉子能装入半径5mm的圆孔");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("宽度20mm的方钉子不能装入半径5mm的圆孔");
        }
    }
}
