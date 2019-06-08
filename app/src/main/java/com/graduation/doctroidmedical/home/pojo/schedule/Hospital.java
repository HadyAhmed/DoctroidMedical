package com.graduation.doctroidmedical.home.pojo.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.graduation.doctroidmedical.home.pojo.hospital.Location;

import java.util.List;

public class Hospital {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("departments")
    @Expose
    private List<String> departments = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Location getLocation() {
        return location;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getV() {
        return v;
    }
}

