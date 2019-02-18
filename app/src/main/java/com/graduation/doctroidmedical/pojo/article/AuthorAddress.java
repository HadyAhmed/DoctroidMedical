package com.graduation.doctroidmedical.pojo.article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorAddress {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
