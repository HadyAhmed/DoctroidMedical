package com.graduation.doctroidmedical.home.pojo.schedule;

public class ScheduleModel {
    private String begin;
    private String hospitalName;
    private String hospitalId;
    private String doctorName;
    private String roomName;
    private String scheduleId;
    private String userId;

    public ScheduleModel(String begin, String hospitalName, String hospitalId, String doctorName, String roomName, String scheduleId, String userId) {
        this.begin = begin;
        this.hospitalName = hospitalName;
        this.hospitalId = hospitalId;
        this.doctorName = doctorName;
        this.roomName = roomName;
        this.scheduleId = scheduleId;
        this.userId = userId;
    }

    public String getBegin() {
        return begin;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public String getUserId() {
        return userId;
    }
}
