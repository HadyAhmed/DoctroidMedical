package com.graduation.doctroidmedical.home.pojo.schedule.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("valid")
    @Expose
    private Boolean valid;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("purchasedAt")
    @Expose
    private String purchasedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getValid() {
        return valid;
    }

    public String getId() {
        return id;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getHospital() {
        return hospital;
    }

    public String getUser() {
        return user;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public Integer getV() {
        return v;
    }
}

