package com.IJN.pojo;

public class Good {
    public int id;
    public String name;
    public double price = 0d;
    public int quantity = 0;

    public Good(){}

    public Good(String name) {
        this.name = name;
    }

    public Good(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Good(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                "}\r\n";
    }
}
