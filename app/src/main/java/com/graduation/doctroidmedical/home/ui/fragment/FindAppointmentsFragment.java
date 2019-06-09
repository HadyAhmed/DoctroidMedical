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
import com.graduation.doctroidmedical.home.pojo.schedule.ScheduleModel;
import com.graduation.doctroidmedical.home.pojo.schedule.ScheduleResponse;
import com.graduation.doctroidmedical.home.pojo.schedule.request.ScheduleRequest;
import com.graduation.doctroidmedical.home.pojo.schedule.response.ScheduleConfirmationResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.graduation.doctroidmedical.home.ui.activity.SignInActivity.USER_ID;

public class FindAppointmentsFragment extends Fragment implements AppointmentAdapter.OnScheduleClickLister {
    private static final String TAG = "FindAppointmentsTag";
    private Context context;
    private WebServices webServices = WebServices.startService.create(WebServices.class);
    private AppointmentAdapter appointmentAdapter = new AppointmentAdapter(this);
    private String uid;
    private FragmentFindAppointmentBinding findAppointmentBinding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        uid = PreferenceManager.getDefaultSharedPreferences(context).getString(USER_ID, "");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        findAppointmentBinding = FragmentFindAppointmentBinding.inflate(inflater, container, false);

        findAppointmentBinding.scheduleRv.setAdapter(appointmentAdapter);

        if (getArguments() != null) {
            webServices.getSchedules(FindAppointmentsFragmentArgs.fromBundle(getArguments()).getDoctorId()).enqueue(new Callback<List<ScheduleResponse>>() {
                @Override
                public void onResponse(@NonNull Call<List<ScheduleResponse>> call, @NonNull Response<List<ScheduleResponse>> response) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        List<ScheduleModel> scheduleModels = new ArrayList<>();
                        for (int i = 0; i < response.body().size(); i++) {
                            String begin = response.body().get(i).getBegin();
                            String hospitalName = response.body().get(i).getRoom().getHospital().getName();
                            String hospitalId = response.body().get(i).getRoom().getHospital().getId();
                            String doctorName = response.body().get(i).getEmployee().getFristName() + ' ' + response.body().get(i).getEmployee().getLastName();
                            String roomName = response.body().get(i).getRoom().getName();
                            scheduleModels.add(new ScheduleModel(begin, hospitalName, hospitalId, doctorName, roomName, response.body().get(i).getId(), uid));
                        }
                        appointmentAdapter.setScheduleModels(scheduleModels);
                        Log.d(TAG, "onResponse: " + response.body().get(0).getAvailable());
                    } else {
                        Toast.makeText(context, "no schedule available for now", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<ScheduleResponse>> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return findAppointmentBinding.getRoot();
    }

    @Override
    public void sendSchedule(View v, String scheduleId, String hospitalId, String uid) {
        findAppointmentBinding.progressCircular.setVisibility(View.VISIBLE);
        ScheduleRequest scheduleRequest = new ScheduleRequest(scheduleId, uid, hospitalId);
        webServices.sendSchedule(scheduleRequest)
                .enqueue(new Callback<ScheduleConfirmationResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ScheduleConfirmationResponse> call, @NonNull Response<ScheduleConfirmationResponse> response) {
                        findAppointmentBinding.progressCircular.setVisibility(View.INVISIBLE);
                        if (response.body() != null) {
                            Toast.makeText(context, "Schedule successfully done", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ScheduleConfirmationResponse> call, @NonNull Throwable t) {
                        findAppointmentBinding.progressCircular.setVisibility(View.INVISIBLE);
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
