package com.agicquel.tp2.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by agicquel on 23/01/18.
 */

public class Task implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private int duration; // in min
    private Date date;

    public Task() {
        this.id = UUID.randomUUID();
    }

    public Task(String title, String description, int duration, Date date) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if(getClass() != obj.getClass())
            return false;
        else if(!((Task) obj).id.equals(id) )
            return false;

        return true;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
