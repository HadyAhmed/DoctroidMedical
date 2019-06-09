package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.graduation.doctroidmedical.databinding.FragmentHospitalBinding;
import com.graduation.doctroidmedical.home.adapter.HospitalAdapter;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalArrayItem;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalFragment extends Fragment implements HospitalAdapter.OnHospitalClickListener {
    private static final String TAG = "HospitalFragment";
    private FragmentHospitalBinding hospitalBinding;
    private HospitalAdapter hospitalAdapter = new HospitalAdapter(this);
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        hospitalBinding = FragmentHospitalBinding.inflate(inflater, container, false);

        hospitalBinding.hospitalRv.setAdapter(hospitalAdapter);

        WebServices.startService.create(WebServices.class)
                .getHospitals().enqueue(new Callback<List<HospitalsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<HospitalsResponse>> call, @NonNull Response<List<HospitalsResponse>> response) {
                hospitalBinding.loadingHospitals.setVisibility(View.INVISIBLE);
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: success");
                    List<HospitalArrayItem> hospitalArrayItem = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        hospitalArrayItem.add(new HospitalArrayItem(response.body().get(i).getId(), response.body().get(i).getName(), response.body().get(i).getPicture(), response.body().get(i).getDepartments()));
                    }
                    Log.d(TAG, "parsing... " + hospitalArrayItem.size());
                    hospitalAdapter.setHospitalItems(hospitalArrayItem);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<HospitalsResponse>> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                hospitalBinding.loadingHospitals.setVisibility(View.INVISIBLE);
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return hospitalBinding.getRoot();
    }

    @Override
    public void onHospitalClick(View v, String hospitalId) {
        Navigation.findNavController(v).navigate(HospitalFragmentDirections.actionHospitalFragmentToHospitalInformationFragment(hospitalId));
    }
}
