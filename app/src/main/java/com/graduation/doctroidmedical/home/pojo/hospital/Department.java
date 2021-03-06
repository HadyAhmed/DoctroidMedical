package com.graduation.doctroidmedical.home.pojo.hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getV() {
        return v;
    }
}
