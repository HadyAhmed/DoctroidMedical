package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.graduation.doctroidmedical.databinding.HospitalInfoFragmentBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalsResponse;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalInformationFragment extends Fragment {
    private HospitalInfoFragmentBinding infoFragmentBinding;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        infoFragmentBinding = HospitalInfoFragmentBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            WebServices.startService.create(WebServices.class)
                    .getHospitalDetail(HospitalInformationFragmentArgs.fromBundle(getArguments()).getHospitalId())
                    .enqueue(new Callback<HospitalsResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<HospitalsResponse> call, @NonNull Response<HospitalsResponse> response) {
                            updateUi(response);
                        }

                        @Override
                        public void onFailure(@NonNull Call<HospitalsResponse> call, @NonNull Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        return infoFragmentBinding.getRoot();
    }

    private void updateUi(@NonNull final Response<HospitalsResponse> response) {
        if (response.body() != null) {
            infoFragmentBinding.setHospitalDetail(response.body());
            for (int i = 0; i < response.body().getDepartments().size(); i++) {
                Chip chip = new Chip(context);
                chip.setText(response.body().getDepartments().get(i).getName());
                infoFragmentBinding.departmentsChips.addView(chip);
            }
            infoFragmentBinding.locationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", response.body().getLocation().getCoordinates().get(0), response.body().getLocation().getCoordinates().get(1));
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }
    }
}
