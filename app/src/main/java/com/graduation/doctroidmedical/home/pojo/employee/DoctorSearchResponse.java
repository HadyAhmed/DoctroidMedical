package com.graduation.doctroidmedical.home.pojo.employee;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DoctorSearchResponse {

    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("qulaifications")
    @Expose
    private List<Object> qulaifications = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("fristName")
    @Expose
    private String fristName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("nationalID")
    @Expose
    private String nationalID;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("joinedOn")
    @Expose
    private String joinedOn;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Address getAddress() {
        return address;
    }

    public List<Object> getQulaifications() {
        return qulaifications;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getFristName() {
        return fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNationalID() {
        return nationalID;
    }

    public String getUserType() {
        return userType;
    }

    public String getHospital() {
        return hospital;
    }

    public String getDepartment() {
        return department;
    }

    public String getJoinedOn() {
        return joinedOn;
    }

    public Integer getV() {
        return v;
    }
}