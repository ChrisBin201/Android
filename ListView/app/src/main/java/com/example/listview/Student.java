package com.example.listview;

public class Student {
    private String name, maSV;

    public Student(String name, String maSV) {
        this.name = name;
        this.maSV = maSV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaSV() {
        return maSV;
    }


    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", maSV='" + maSV + '\'' + '}';
    }
}
