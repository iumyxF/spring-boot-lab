package com.example.design.observer_pattern.test03;

/**
 * @author iumyxF
 * @description: 华尔街 股票机构具体实现
 * @date 2024/1/11 15:42
 */
public class WallStreet extends StockInstitutions {

    @Override
    public void change() {
        System.out.println("股票价格变化幅度达到5%，通知韭菜们");
        for (Observer sucker : suckers) {
            sucker.bargainHunting();
        }
    }
}
