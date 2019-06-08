package com.graduation.doctroidmedical.home.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.databinding.FragmentAppoinmentAndComplainsBinding;

public class AppointmentAndComplainsFragment extends Fragment implements View.OnClickListener {
    private FragmentAppoinmentAndComplainsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAppoinmentAndComplainsBinding.inflate(inflater, container, false);

        binding.navToAppointmentBtn.setOnClickListener(this);
        binding.navToComplainsBtn.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.navToComplainsBtn.getId()) {
            Navigation.findNavController(v).navigate(R.id.action_appointmentAndComplainsFragment_to_complainsFragment);
        } else {
            Navigation.findNavController(v).navigate(R.id.action_appointmentAndComplainsFragment_to_appointmentFragment);
        }
    }
}
