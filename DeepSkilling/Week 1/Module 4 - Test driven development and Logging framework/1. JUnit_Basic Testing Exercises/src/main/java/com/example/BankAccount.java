package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 4: A class used to demonstrate AAA pattern and test fixtures.
 */
public class BankAccount {

    private double balance;
    private final List<String> transactionLog = new ArrayList<>();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        transactionLog.add("Account opened with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactionLog.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
        transactionLog.add("Withdrew: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionLog() {
        return transactionLog;
    }
}
