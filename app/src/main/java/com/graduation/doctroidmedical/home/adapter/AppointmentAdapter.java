package com.graduation.doctroidmedical.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.doctroidmedical.databinding.ScheduleItemBinding;
import com.graduation.doctroidmedical.home.pojo.schedule.ScheduleModel;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    private List<ScheduleModel> scheduleModels;
    private LayoutInflater inflater;
    private OnScheduleClickLister onScheduleClickLister;
    private boolean isItProfile;

    public AppointmentAdapter(boolean isItProfile, OnScheduleClickLister onScheduleClickLister) {
        this.onScheduleClickLister = onScheduleClickLister;
        this.isItProfile = isItProfile;
    }

    public void setScheduleModels(List<ScheduleModel> scheduleModels) {
        this.scheduleModels = scheduleModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new AppointmentViewHolder(ScheduleItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        holder.setSchedule(scheduleModels.get(position));
        holder.setScheduleClickListener(onScheduleClickLister);
        holder.showScheduleButton(isItProfile);
    }

    @Override
    public int getItemCount() {
        if (scheduleModels == null) {
            return 0;
        }
        return scheduleModels.size();
    }

    public interface OnScheduleClickLister {
        void sendSchedule(View v, String scheduleId, String hospitalId, String uid);
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {
        private ScheduleItemBinding scheduleItemBinding;

        AppointmentViewHolder(@NonNull ScheduleItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.scheduleItemBinding = itemBinding;
        }

        void setSchedule(ScheduleModel schedule) {
            this.scheduleItemBinding.setScheduleModel(schedule);
        }

        void setScheduleClickListener(OnScheduleClickLister scheduleClickListener) {
            this.scheduleItemBinding.setOnScheduleClick(scheduleClickListener);
        }

        void showScheduleButton(boolean showSchedule) {
            this.scheduleItemBinding.addScheduleBtn.setVisibility(showSchedule ? View.VISIBLE : View.INVISIBLE);
        }
    }
}
