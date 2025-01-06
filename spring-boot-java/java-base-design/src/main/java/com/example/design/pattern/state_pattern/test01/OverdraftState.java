package com.example.design.pattern.state_pattern.test01;

/**
 * @author iumyxF
 * @description:
 * @date 2023/8/14 17:32
 */
public class OverdraftState extends AccountState{
    public OverdraftState(AccountState state) {
        this.acc = state.acc;
    }
    public void deposit(double amount) {
        acc.setBalance(acc.getBalance() + amount);
        stateCheck();
    }
    public void withdraw(double amount) {
        acc.setBalance(acc.getBalance() - amount);
        stateCheck();
    }
    public void computeInterest() {
        System.out.println("计算利息！");
    }

    public void stateCheck() {
        if (acc.getBalance() > 0) {
            acc.setState(new NormalState(this));
        }
        else if (acc.getBalance() == -2000) {
            acc.setState(new RestrictedState(this));
        }
        else if (acc.getBalance() < -2000) {
            System.out.println("操作受限！");
        }
    }
}
