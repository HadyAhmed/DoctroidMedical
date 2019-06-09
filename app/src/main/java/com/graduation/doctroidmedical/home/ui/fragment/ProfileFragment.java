package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.graduation.doctroidmedical.databinding.FragmentProfileBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.profile.ProfileResponse;
import com.graduation.doctroidmedical.home.ui.activity.SignInActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private FragmentProfileBinding profileBinding;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false);

        WebServices.startService.create(WebServices.class)
                .getUserDetails("Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(SignInActivity.USER_TOKEN, ""))
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                        if (response.body() != null) {
                            profileBinding.setProfile(response.body().getResult().getData());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        profileBinding.appointmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 6/9/2019 show appointments here
            }
        });
        profileBinding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
                startActivity(new Intent(context, SignInActivity.class));
                if (getActivity() != null) {
                    getActivity().finish();
                }
            }
        });
        return profileBinding.getRoot();
    }
}
