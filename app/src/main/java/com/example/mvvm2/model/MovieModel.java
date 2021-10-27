package com.example.mvvm2.model;

public class MovieModel {

    private String title;
    private String url;

    public MovieModel(String title, String image) {
        this.title = title;
        this.url = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return url;
    }

    public void setImage(String image) {
        this.url = url;
    }
}
