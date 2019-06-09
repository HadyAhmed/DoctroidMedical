package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.graduation.doctroidmedical.databinding.FragmentFindAppointmentBinding;
import com.graduation.doctroidmedical.home.adapter.AppointmentAdapter;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.appointment.AppointmentsResponse;
import com.graduation.doctroidmedical.home.pojo.schedule.ScheduleModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.graduation.doctroidmedical.home.ui.activity.SignInActivity.USER_ID;

public class AppointmentHistory extends Fragment {
    private static final String TAG = "AppointmentHistory";
    private AppointmentAdapter appointmentAdapter = new AppointmentAdapter(false, null);
    private Context context;
    private FragmentFindAppointmentBinding findAppointmentBinding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        findAppointmentBinding = FragmentFindAppointmentBinding.inflate(inflater, container, false);

        findAppointmentBinding.scheduleRv.setAdapter(appointmentAdapter);
        findAppointmentBinding.progressCircular.setVisibility(View.VISIBLE);
        WebServices.startService.create(WebServices.class)
                .getUserAppointments(PreferenceManager.getDefaultSharedPreferences(context).getString(USER_ID, ""))
                .enqueue(new Callback<AppointmentsResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<AppointmentsResponse> call, @NonNull Response<AppointmentsResponse> response) {
                        findAppointmentBinding.progressCircular.setVisibility(View.INVISIBLE);
                        if (response.body() != null && !response.body().getResult().isEmpty()) {
                            List<ScheduleModel> scheduleModels = new ArrayList<>();
                            for (int i = 0; i < response.body().getResult().size(); i++) {
                                String hospitalName = response.body().getResult().get(i).getHospital().getName();
                                String begin = response.body().getResult().get(i).getSchedule().getBegin();
                                String employeeName = response.body().getResult().get(i).getSchedule().getEmployee().getFristName() + ' ' + response.body().getResult().get(i).getSchedule().getEmployee().getLastName();
                                String roomNumber = response.body().getResult().get(i).getSchedule().getRoom().getName();
                                scheduleModels.add(new ScheduleModel(begin, hospitalName, employeeName, roomNumber));
                            }
                            appointmentAdapter.setScheduleModels(scheduleModels);
                        } else {
                            findAppointmentBinding.progressCircular.setVisibility(View.INVISIBLE);
                            Toast.makeText(context, "no schedule available for now", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AppointmentsResponse> call, @NonNull Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getMessage());
                    }
                });
        return findAppointmentBinding.getRoot();
    }
}
