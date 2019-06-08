package com.graduation.doctroidmedical.home.pojo.article;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ArticleResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("__v")
    @Expose
    private int v;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getPicture() {
        return picture;
    }

    public Author getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public int getV() {
        return v;
    }
}

