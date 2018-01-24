package com.agicquel.popcorntime.model;

import android.support.annotation.DrawableRes;

/**
 * Created by agicquel on 24/01/18.
 */

public class Cinema {
    @DrawableRes private int imageSrc;
    private String title;
    private String director;
    private int duration;

    public Cinema(@DrawableRes int imageSrc, String title, String director, int duration) {
        this.imageSrc = imageSrc;
        this.title = title;
        this.director = director;
        this.duration = duration;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}