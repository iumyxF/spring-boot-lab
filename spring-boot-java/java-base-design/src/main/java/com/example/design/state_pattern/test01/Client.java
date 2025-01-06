package com.example.design.state_pattern.test01;

/**
 * @author iumyxF
 * @description:
 * @date 2023/8/14 17:34
 */
class Client {
    public static void main(String[] args) {
        Account acc = new Account("段誉", 0.0);
        acc.deposit(1000);
        acc.withdraw(2000);
        acc.deposit(3000);
        acc.withdraw(4000);
        acc.withdraw(1000);
        acc.computeInterest();
    }
}
