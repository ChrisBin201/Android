package com.example.th1;

public class Project {
    private String name;
    private String startDate;
    private String endDate;
    private boolean completed;

    public Project() {
    }

    public Project(String name, String startDate, String endDate, boolean completed) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
