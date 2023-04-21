package com.example.ontapth.model;

public class User {
    private int img;
    private String name;
    private String favor;

    public User() {
    }

    public User(int img, String name, String favor) {
        this.img = img;
        this.name = name;
        this.favor = favor;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavor() {
        return favor;
    }

    public void setFavor(String favor) {
        this.favor = favor;
    }
}
