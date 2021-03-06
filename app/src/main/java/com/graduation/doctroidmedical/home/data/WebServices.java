package com.graduation.doctroidmedical.home.data;

import com.google.gson.Gson;
import com.graduation.doctroidmedical.home.pojo.appointment.AppointmentsResponse;
import com.graduation.doctroidmedical.home.pojo.article.ArticleResponse;
import com.graduation.doctroidmedical.home.pojo.complain.request.ComplainBody;
import com.graduation.doctroidmedical.home.pojo.complain.response.ComplainResponse;
import com.graduation.doctroidmedical.home.pojo.employee.DoctorSearchResponse;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalsResponse;
import com.graduation.doctroidmedical.home.pojo.loginresponse.LoginModel;
import com.graduation.doctroidmedical.home.pojo.loginresponse.LoginResponse;
import com.graduation.doctroidmedical.home.pojo.profile.ProfileResponse;
import com.graduation.doctroidmedical.home.pojo.room.Room;
import com.graduation.doctroidmedical.home.pojo.schedule.ScheduleResponse;
import com.graduation.doctroidmedical.home.pojo.schedule.request.ScheduleRequest;
import com.graduation.doctroidmedical.home.pojo.schedule.response.ScheduleConfirmationResponse;
import com.graduation.doctroidmedical.home.pojo.signupresponse.SignUpResponse;
import com.graduation.doctroidmedical.home.pojo.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServices {
    // The base URL for the web service
    String BASE_URL = "http://healthapi.herokuapp.com";
    // Paths for various APIs
    String USER = "user";
    String SIGN_IN = "login";
    String SIGN_UP = "signup";
    String ARTICLE = "article";
    String IMAGE_PATH = "/uploads/";

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

    @GET(ARTICLE)
    Call<List<ArticleResponse>> getArticles();

    @GET(ARTICLE + "/{articleId}")
    Call<ArticleResponse> getArticleDetail(@Path("articleId") String articleId);

    @GET("hospital")
    Call<List<HospitalsResponse>> getHospitals();

    @GET("hospital/{id}")
    Call<HospitalsResponse> getHospitalDetail(@Path("id") String id);

    @GET("room/byHospital/{hospitalId}")
    Call<List<Room>> getRooms(@Path("hospitalId") String hospitalId);

    @GET("employee/byRoom/{doctorId}")
    Call<List<DoctorSearchResponse>> getDoctors(@Path("doctorId") String doctorId);

    @GET("schedule/byEmployee/{doctorId}")
    Call<List<ScheduleResponse>> getSchedules(@Path("doctorId") String doctorId);

    @GET("user/profile")
    Call<ProfileResponse> getUserDetails(@Header("Authorization") String token);

    @POST("complain")
    Call<ComplainResponse> sendComplain(@Body ComplainBody complainBody);

    @POST("appointment")
    Call<ScheduleConfirmationResponse> sendSchedule(@Body ScheduleRequest scheduleRequest);

    @GET("appointment/history/{userId}")
    Call<AppointmentsResponse> getUserAppointments(@Path("userId") String uid);
}
