package com.graduation.doctroidmedical.home.pojo.schedule.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleRequest {
    @SerializedName("schedule")
    @Expose
    private String scheduleId;
    @SerializedName("user")
    @Expose
    private String userId;
    @SerializedName("hospital")
    @Expose
    private String hospitalId;

    public ScheduleRequest(String scheduleId, String userId, String hospitalId) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.hospitalId = hospitalId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getHospitalId() {
        return hospitalId;
    }
}
