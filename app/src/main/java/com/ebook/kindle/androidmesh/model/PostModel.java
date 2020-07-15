package com.ebook.kindle.androidmesh.model;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    private int userId;
    private Integer id;
    private String title;

    @SerializedName("body")
    private String text;

    public PostModel(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
