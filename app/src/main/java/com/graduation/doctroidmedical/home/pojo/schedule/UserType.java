package com.graduation.doctroidmedical.home.pojo.schedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserType {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("accessLevel")
    @Expose
    private Integer accessLevel;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getName() {
        return name;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public Integer getV() {
        return v;
    }

    public String getId() {
        return id;
    }
}
