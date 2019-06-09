package com.graduation.doctroidmedical.home.pojo.complain.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("answered")
    @Expose
    private Boolean answered;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getAnswered() {
        return answered;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getUser() {
        return user;
    }

    public String getHospital() {
        return hospital;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getV() {
        return v;
    }
}

