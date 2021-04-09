package com.IJN.pojo;

public class User {
    public int id;
    public String username;
    public String password;
    public double balance = 0d;
    public double creditCardBalance = 2000d;
    public int access = 0;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public User(String username, String password, double balance, double creditCardBalance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.creditCardBalance = creditCardBalance;
    }

    public User(String username, String password, double balance, double creditCardBalance, int access) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.creditCardBalance = creditCardBalance;
        this.access = access;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", creditCardBalance=" + creditCardBalance +
                "}\r\n";
    }
}
