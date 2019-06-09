package com.graduation.doctroidmedical.home.pojo.complain.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComplainBody {
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("hospital")
    @Expose
    private String hospital;

    public ComplainBody(String body, String user, String hospital) {
        this.body = body;
        this.user = user;
        this.hospital = hospital;
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

    @Override
    public String toString() {
        return "ComplainBody{" +
                "body='" + body + '\'' +
                "\nuser='" + user + '\'' +
                "\nhospital='" + hospital + '\'' +
                '}';
    }
}
