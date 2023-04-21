package com.example.ontapth.model;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;
    private String date; //yyyy-MM-dd for SQLite
    private String status;
    private boolean collab;

    public Task() {
    }

    public Task(int id, String name, String description, String date, String status, boolean collab) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
        this.collab = collab;
    }

    public Task(String name, String description, String date, String status, boolean collab) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
        this.collab = collab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCollab() {
        return collab;
    }

    public void setCollab(boolean collab) {
        this.collab = collab;
    }
}