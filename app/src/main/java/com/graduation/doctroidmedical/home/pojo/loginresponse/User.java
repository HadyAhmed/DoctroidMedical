package com.graduation.doctroidmedical.home.pojo.loginresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}

