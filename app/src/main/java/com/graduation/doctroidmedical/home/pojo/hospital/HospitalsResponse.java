package com.graduation.doctroidmedical.home.pojo.hospital;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HospitalsResponse {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("departments")
    @Expose
    private List<Department> departments = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getPicture() {
        return picture;
    }

    public Location getLocation() {
        return location;
    }

    public List<Department> getDepartments() {
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

