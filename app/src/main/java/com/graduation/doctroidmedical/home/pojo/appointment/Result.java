package com.graduation.doctroidmedical.home.pojo.appointment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graduation.doctroidmedical.home.pojo.schedule.Hospital;

public class Result {

    @SerializedName("valid")
    @Expose
    private Boolean valid;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("schedule")
    @Expose
    private Schedule schedule;
    @SerializedName("hospital")
    @Expose
    private Hospital hospital;
    @SerializedName("user")
    @Expose
    private User user;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public User getUser() {
        return user;
    }

    public String getPurchasedAt() {
        return purchasedAt;
    }

    public Integer getV() {
        return v;
    }
}

