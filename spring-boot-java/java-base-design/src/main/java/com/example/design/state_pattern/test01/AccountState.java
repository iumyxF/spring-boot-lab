package com.example.design.state_pattern.test01;

/**
 * The type Account state.
 *
 * @author iumyxF
 * @description:
 * @date 2023 /8/14 17:32
 */
public abstract class AccountState {
    /**
     * The Acc.
     */
    protected Account acc;

    /**
     * Deposit.
     *
     * @param amount the amount
     */
    public abstract void deposit(double amount);

    /**
     * Withdraw.
     *
     * @param amount the amount
     */
    public abstract void withdraw(double amount);

    /**
     * Compute interest.
     */
    public abstract void computeInterest();

    /**
     * State check.
     */
    public abstract void stateCheck();
}
