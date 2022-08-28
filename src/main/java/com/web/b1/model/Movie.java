package com.web.b1.model;

public class Movie {
    private boolean adult;
    private String original_title;
    private double popularity;
    private String video;

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Movie(boolean adult, String original_title, double popularity, String video) {
        this.adult = adult;
        this.original_title = original_title;
        this.popularity = popularity;
        this.video = video;
    }
}
