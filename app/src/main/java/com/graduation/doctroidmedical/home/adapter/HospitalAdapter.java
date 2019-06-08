package com.graduation.doctroidmedical.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.doctroidmedical.databinding.HospitalItemBinding;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalArrayItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    private List<HospitalArrayItem> hospitalItems;
    private LayoutInflater inflater;

    public void setHospitalItems(List<HospitalArrayItem> hospitalItems) {
        this.hospitalItems = hospitalItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new HospitalViewHolder(HospitalItemBinding.inflate(inflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder hospitalViewHolder, int i) {
        Picasso.get().load("https://healthapi.herokuapp.com/upload" + hospitalItems.get(i).getPicture()).into(hospitalViewHolder.getHospitalItemBinding().hospitalImage);
        hospitalViewHolder.getHospitalItemBinding().hospitalName.setText(hospitalItems.get(i).getName());
        hospitalViewHolder.getHospitalItemBinding().departmentsName.setText("");
        for (int j = 0; j < hospitalItems.get(i).getDepartmentList().size(); j++) {
            hospitalViewHolder.getHospitalItemBinding().departmentsName.append(hospitalItems.get(i).getDepartmentList().get(j).getName() + ", ");
        }

    }

    @Override
    public int getItemCount() {
        if (hospitalItems == null) {
            return 0;
        }
        return hospitalItems.size();
    }

    class HospitalViewHolder extends RecyclerView.ViewHolder {
        private HospitalItemBinding hospitalItemBinding;

        HospitalViewHolder(@NonNull HospitalItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.hospitalItemBinding = itemBinding;
        }

        HospitalItemBinding getHospitalItemBinding() {
            return hospitalItemBinding;
        }
    }
}
