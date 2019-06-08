package com.graduation.doctroidmedical.home.pojo.signupresponse;


import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private CreatedProduct user;

    public String getMessage() {
        return message;
    }

    public CreatedProduct getUser() {
        return user;
    }

    @NonNull
    @Override
    public String toString() {
        return "SignUpResponse{" +
                "message='" + message + '\'' +
                ", user=" + user +
                '}';
    }

}



