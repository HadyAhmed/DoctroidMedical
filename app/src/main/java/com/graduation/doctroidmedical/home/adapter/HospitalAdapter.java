package com.graduation.doctroidmedical.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
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
    private OnHospitalClickListener onHospitalClickListener;

    public HospitalAdapter(OnHospitalClickListener onHospitalClickListener) {
        this.onHospitalClickListener = onHospitalClickListener;
    }

    public void setHospitalItems(List<HospitalArrayItem> hospitalItems) {
        this.hospitalItems = hospitalItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder hospitalViewHolder, int i) {
        hospitalViewHolder.setOnHospitalClick(onHospitalClickListener);
        hospitalViewHolder.setHospital(hospitalItems.get(i));
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new HospitalViewHolder(HospitalItemBinding.inflate(inflater, viewGroup, false));
    }

    public interface OnHospitalClickListener {
        void onHospitalClick(View v, String hospitalId);
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

        void setOnHospitalClick(OnHospitalClickListener onHospitalClick) {
            this.hospitalItemBinding.setHospitalClickListener(onHospitalClick);
        }

        void setHospital(HospitalArrayItem hospital) {
            this.hospitalItemBinding.setHospital(hospital);
            Picasso.get().load("https://healthapi.herokuapp.com/upload" + hospital.getPicture()).into(this.hospitalItemBinding.hospitalImage);
            this.hospitalItemBinding.departmentsName.setText("");
            for (int j = 0; j < hospital.getDepartmentList().size(); j++) {
                hospitalItemBinding.departmentsName.append(hospital.getDepartmentList().get(j).getName() + ", ");
            }
        }
    }
}
