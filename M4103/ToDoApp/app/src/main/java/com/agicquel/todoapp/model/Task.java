package com.agicquel.todoapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by agicquel on 23/01/18.
 * Simple task object
 */

public class Task implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private int duration; // in min
    private Date date;
    private String context;
    private String link;

    public Task() {
        this.id = UUID.randomUUID();
    }

    public Task(String title, String description, int duration, Date date, String context, String link) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.context = context;
        this.link = link;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
