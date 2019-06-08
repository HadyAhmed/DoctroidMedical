package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.graduation.doctroidmedical.databinding.FragmentAppointmentBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.employee.DoctorSearchResponse;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalsResponse;
import com.graduation.doctroidmedical.home.pojo.room.Room;
import com.graduation.doctroidmedical.home.pojo.schedule.ScheduleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentFragment extends Fragment {
    private static final String TAG = "AppointmentFragment";
    private FragmentAppointmentBinding appointmentBinding;
    private Context context;
    private List<String> hospitalsId = new ArrayList<>();
    private List<String> roomsId = new ArrayList<>();
    private List<String> doctorsId = new ArrayList<>();
    private WebServices webService = WebServices.startService.create(WebServices.class);
    private String doctorId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        appointmentBinding = FragmentAppointmentBinding.inflate(inflater, container, false);

        fetchHospitals();

        appointmentBinding.hospitalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fetchRooms(hospitalsId.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        appointmentBinding.roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fetchDoctors(roomsId.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        appointmentBinding.doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctorId = doctorsId.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        appointmentBinding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchSchedule(doctorId);
            }
        });
        return appointmentBinding.getRoot();
    }

    private void fetchSchedule(String doctorId) {
        Log.d(TAG, "fetchSchedule: " + doctorId);
        appointmentBinding.searching.setVisibility(View.VISIBLE);
        webService.getSchedules(doctorId).enqueue(new Callback<List<ScheduleResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<ScheduleResponse>> call, @NonNull Response<List<ScheduleResponse>> response) {
                appointmentBinding.searching.setVisibility(View.INVISIBLE);
                if (response.body() != null && !response.body().isEmpty()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.d(TAG, "Searching for schedule" + response.body().get(i).getEmployee().getFristName());
                    }
                } else {
                    Toast.makeText(context, "no schedule available for now", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ScheduleResponse>> call, @NonNull Throwable t) {
                appointmentBinding.searching.setVisibility(View.INVISIBLE);
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchHospitals() {
        webService.getHospitals().enqueue(new Callback<List<HospitalsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<HospitalsResponse>> call, @NonNull Response<List<HospitalsResponse>> response) {
                if (response.body() != null) {
                    List<String> hospitalsName = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        hospitalsName.add(response.body().get(i).getName());
                        hospitalsId.add(response.body().get(i).getId());
                    }
                    ArrayAdapter<String> hospitalAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, hospitalsName);
                    appointmentBinding.hospitalSpinner.setAdapter(hospitalAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<HospitalsResponse>> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchRooms(String s) {
        Log.d(TAG, "fetching rooms hospital with id: " + s);
        webService.getRooms(s).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(@NonNull Call<List<Room>> call, @NonNull Response<List<Room>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    List<String> roomNames = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        roomNames.add(response.body().get(i).getName());
                        Log.d(TAG, "doctor name: " + response.body().get(i).getName());
                        roomsId.add(response.body().get(i).getId());
                    }
                    ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, roomNames);
                    appointmentBinding.roomSpinner.setAdapter(roomAdapter);

                } else {
                    Toast.makeText(context, "no rooms available for this hospital", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Room>> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchDoctors(String s) {
        Log.d(TAG, "fetching doctor with id: " + s);
        webService.getDoctors(s).enqueue(new Callback<List<DoctorSearchResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<DoctorSearchResponse>> call, @NonNull Response<List<DoctorSearchResponse>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    List<String> doctorNames = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        doctorNames.add(response.body().get(i).getFristName() + ' ' + response.body().get(i).getLastName());
                        Log.d(TAG, "doctor name: " + response.body().get(i).getFristName());
                        roomsId.add(response.body().get(i).getId());
                        doctorsId.add(response.body().get(i).getId());
                    }
                    ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, doctorNames);
                    appointmentBinding.doctorSpinner.setAdapter(doctorAdapter);
                } else {
                    Toast.makeText(context, "no doctor available for this room", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DoctorSearchResponse>> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
