package com.example.crudviewpagertablayout.model;

public class Cat {
    private int image;
    private String name, describe;
    private double price;

    public Cat(int image, String name, String describe, double price) {
        this.image = image;
        this.name = name;
        this.describe = describe;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

