package com.example.intentexplicit;

import java.io.Serializable;

public class Student implements Serializable {
    private int img;
    private String name;
    private String age;
    //constructor
    public Student(int img, String name, String age) {
        this.img = img;
        this.name = name;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
