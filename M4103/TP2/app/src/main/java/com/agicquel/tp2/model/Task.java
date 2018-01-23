package com.agicquel.tp2.model;

import java.util.Date;

/**
 * Created by agicquel on 23/01/18.
 */

public class Task {
    private String title;
    private String description;
    private int duration; // in min
    private Date date; // beginning date

    public Task(String title, String description, int duration, Date date) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
