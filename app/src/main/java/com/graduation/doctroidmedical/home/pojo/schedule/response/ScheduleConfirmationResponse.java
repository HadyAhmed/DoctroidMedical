package com.graduation.doctroidmedical.home.pojo.schedule.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleConfirmationResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("result")
    @Expose
    private Result result;

    public String getMessage() {
        return message;
    }

    public Result getResult() {
        return result;
    }
}