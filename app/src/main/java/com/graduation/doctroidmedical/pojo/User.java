package com.graduation.doctroidmedical.pojo;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

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
    private long dateOfBirth;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("address")
    @Expose
    private Address address;

    public User(String firstName, String lastName, String email, String phoneNumber,
                Address address, String password, String gender, long dateOfBirth) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.fristName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Address getAddress() {
        return address;
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

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    @NonNull
    @Override
    public String toString() {
        return "User{" +
                " \nemail='" + email + '\n' +
                ", password='" + password + '\n' +
                ", gender='" + gender + '\n' +
                ", fristName='" + fristName + '\n' +
                ", lastName='" + lastName + '\n' +
                ", dateOfBirth='" + dateOfBirth + '\n' +
                ", phoneNumber='" + phoneNumber + '\n' +
                ", address=" + address +
                '}';
    }
}


