package com.web.b1.model;

public class MovieRow {
    private boolean adult;
    private String original_title;
    private Double popularity;
    private boolean video;

    private Integer id;

    public MovieRow() {}
    public MovieRow(boolean adult, String original_title, Double popularity, boolean video, Integer id) {
        this.adult = adult;
        this.original_title = original_title;
        this.popularity = popularity;
        this.video = video;
        this.id = id;
    }

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

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
