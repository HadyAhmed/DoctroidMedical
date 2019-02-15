package com.graduation.doctroidmedical.utils;

import com.google.gson.Gson;
import com.graduation.doctroidmedical.pojo.LoginModel;
import com.graduation.doctroidmedical.pojo.LoginResponse;
import com.graduation.doctroidmedical.pojo.SignUpResponse;
import com.graduation.doctroidmedical.pojo.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebServices {
    // The base URL for the web service
    String BASE_URL = "http://healthapi.herokuapp.com";

    // Paths for various APIs
    String USER = "user";
    String SIGN_IN = "login";
    String SIGN_UP = "signup";
    Retrofit startService = new Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();

    @POST(USER + '/' + SIGN_IN)
    Call<LoginResponse> checkEntryValidation(@Body LoginModel userEntries);

    @Headers("Content-Type: application/json")
    @POST(USER + '/' + SIGN_UP)
    Call<SignUpResponse> createAccount(@Body User user);

}
