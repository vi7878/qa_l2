package org.example;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Початковий баланс не може бути від'ємним");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Від'ємна сума поповнення");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Від'ємна сума зняття");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Недостатньо коштів на рахунку");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
