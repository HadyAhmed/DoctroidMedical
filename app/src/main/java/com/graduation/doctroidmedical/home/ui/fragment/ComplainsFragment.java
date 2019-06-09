package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.graduation.doctroidmedical.databinding.FragmentComplainsBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.complain.request.ComplainBody;
import com.graduation.doctroidmedical.home.pojo.complain.response.ComplainResponse;
import com.graduation.doctroidmedical.home.pojo.hospital.HospitalsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.graduation.doctroidmedical.home.ui.activity.SignInActivity.USER_ID;

public class ComplainsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "ComplainsFragment";
    private FragmentComplainsBinding complainsBinding;
    private WebServices webServices = WebServices.startService.create(WebServices.class);
    private Context context;
    private List<String> hospitalsId = new ArrayList<>();
    private int selectedHospitalPosition;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        complainsBinding = FragmentComplainsBinding.inflate(inflater, container, false);

        fetchHospitals();

        complainsBinding.sendComplainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hospitalsId.isEmpty() && !complainsBinding.complainEt.getText().toString().equals("")) {
                    complainsBinding.progressBar.setVisibility(View.VISIBLE);
                    webServices.sendComplain(new ComplainBody(
                            complainsBinding.complainEt.getText().toString(),
                            PreferenceManager.getDefaultSharedPreferences(context).getString(USER_ID, ""),
                            hospitalsId.get(selectedHospitalPosition)))
                            .enqueue(new Callback<ComplainResponse>() {
                                @Override
                                public void onResponse(@NonNull Call<ComplainResponse> call, @NonNull Response<ComplainResponse> response) {
                                    complainsBinding.progressBar.setVisibility(View.INVISIBLE);
                                    if (response.body() != null) {
                                        Toast.makeText(context, "your feedback was sent successfully", Toast.LENGTH_LONG).show();
                                        complainsBinding.complainEt.setText("");
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<ComplainResponse> call, @NonNull Throwable t) {
                                    complainsBinding.progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(context, "make sure to select hospital and write your message before sending empty one", Toast.LENGTH_SHORT).show();
                }
            }
        });
        complainsBinding.hospitalSpinner.setOnItemSelectedListener(this);

        return complainsBinding.getRoot();
    }

    private void fetchHospitals() {
        webServices.getHospitals().enqueue(new Callback<List<HospitalsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<HospitalsResponse>> call, @NonNull Response<List<HospitalsResponse>> response) {
                if (response.body() != null) {
                    List<String> hospitalNames = new ArrayList<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        hospitalNames.add(response.body().get(i).getName());
                        hospitalsId.add(response.body().get(i).getId());
                    }
                    ArrayAdapter<String> hospitalAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, hospitalNames);
                    complainsBinding.hospitalSpinner.setAdapter(hospitalAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<HospitalsResponse>> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedHospitalPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
