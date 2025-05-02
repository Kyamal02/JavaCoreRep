package ru.itis.multithreading.ex2;

public class Account {
    private boolean isAdmin;
    private String name;
    private Long balance;

    public Account(boolean isAdmin, String name, Long balance) {
        this.isAdmin = isAdmin;
        this.name = name;
        this.balance = balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getName() {
        return name;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
