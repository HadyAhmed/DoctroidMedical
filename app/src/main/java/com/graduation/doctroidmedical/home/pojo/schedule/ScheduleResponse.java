package com.graduation.doctroidmedical.home.pojo.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graduation.doctroidmedical.home.pojo.room.Room;


public class ScheduleResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("begin")
    @Expose
    private String begin;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("numberOfMaxAppointments")
    @Expose
    private Integer numberOfMaxAppointments;
    @SerializedName("available")
    @Expose
    private Boolean available;
    @SerializedName("room")
    @Expose
    private Room room;
    @SerializedName("employee")
    @Expose
    private Employee employee;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public Integer getNumberOfMaxAppointments() {
        return numberOfMaxAppointments;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Room getRoom() {
        return room;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getV() {
        return v;
    }
}

