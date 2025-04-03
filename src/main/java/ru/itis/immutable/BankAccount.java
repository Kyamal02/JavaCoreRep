package ru.itis.immutable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private final double balance;//?


    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }
}
