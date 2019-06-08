package com.graduation.doctroidmedical.home.pojo.hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {

    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = null;
    @SerializedName("type")
    @Expose
    private String type;

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public String getType() {
        return type;
    }
}
