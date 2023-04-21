package com.example.myapplication.model;

public class Techology {
    private int img;
    private String name,sub, desc;
    public Techology(){}
    public Techology(String name, String sub, String desc, int img){
        this.img = img;
        this.name = name;
        this.sub = sub;
        this.desc = desc;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getSub() {
        return sub;
    }

    public String getDesc() {
        return desc;
    }
}
