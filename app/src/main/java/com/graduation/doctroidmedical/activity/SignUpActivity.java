package com.graduation.doctroidmedical.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.databinding.ActivitySignUpBinding;
import com.graduation.doctroidmedical.pojo.Address;
import com.graduation.doctroidmedical.pojo.SignUpResponse;
import com.graduation.doctroidmedical.pojo.User;
import com.graduation.doctroidmedical.utils.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = SignUpActivity.class.getSimpleName() + "WebServices";
    private ActivitySignUpBinding signUpBinding;
    private String gender;
    private WebServices services = WebServices.startService.create(WebServices.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        signUpBinding.signUpToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        signUpBinding.genderSpinner.setOnItemSelectedListener(this);
        signUpBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEntries();
            }
        });
    }

    private void checkEntries() {
        Log.d(TAG, "checkEntries: processing...");
        // Setting Values 
        String firstName = signUpBinding.firstNameEt.getText().toString();
        String lastName = signUpBinding.lastNameEt.getText().toString();
        String email = signUpBinding.emailEt.getText().toString();
        String phoneNumber = signUpBinding.mobileEt.getText().toString();
        String password = signUpBinding.passwordEt.getText().toString();
        String repeatPassword = signUpBinding.confirmPasswordEt.getText().toString();
        String city = signUpBinding.cityAddressEt.getText().toString();
        String state = signUpBinding.stateAddressEt.getText().toString();
        String street = signUpBinding.streetAddressEt.getText().toString();
        // TODO: 2019-02-15 Add Date
        long dateOfBirth = 1548166535574L;

        if (TextUtils.isEmpty(firstName)) {
            signUpBinding.firstNameEt.setError("First Name Required");
            Log.d(TAG, "checkEntries: error for " + firstName);
        } else if (TextUtils.isEmpty(lastName)) {
            signUpBinding.lastNameEt.setError("Last Name Required");
            Log.d(TAG, "checkEntries: error for " + lastName);
        } else if (TextUtils.isEmpty(email) || !email.endsWith(".com")) {
            signUpBinding.emailEt.setError("Empty Or Invalid");
            Log.d(TAG, "checkEntries: error for " + email);
        } else if (TextUtils.isEmpty(phoneNumber) && TextUtils.isDigitsOnly(phoneNumber)) {
            signUpBinding.mobileEt.setError("Phone Number Is Empty Or Invalid");
            Log.d(TAG, "checkEntries: error for " + phoneNumber);
        } else if (TextUtils.isEmpty(city)) {
            signUpBinding.cityAddressEt.setError("City Required");
            Log.d(TAG, "checkEntries: error for " + city);
        } else if (TextUtils.isEmpty(state)) {
            signUpBinding.stateAddressEt.setError("State Required");
            Log.d(TAG, "checkEntries: error for " + state);
        } else if (TextUtils.isEmpty(street)) {
            signUpBinding.streetAddressEt.setError("Street Required");
            Log.d(TAG, "checkEntries: error for " + street);
        } else if (TextUtils.isEmpty(password)) {
            signUpBinding.passwordEt.setError("Password Required");
            Log.d(TAG, "checkEntries: error for " + password);
        } else if (TextUtils.isEmpty(repeatPassword)) {
            signUpBinding.passwordEt.setError("Password Required");
            Log.d(TAG, "checkEntries: error for " + password);
        } else if (!password.equals(repeatPassword)) {
            Toast.makeText(this, "Password not matching", Toast.LENGTH_SHORT).show();
        } else {
            checkAndCreateAccount(firstName, lastName, email, phoneNumber, password, city, state, street, dateOfBirth);
        }
    }

    private void checkAndCreateAccount(String firstName, String lastName, String email,
                                       String phoneNumber, String password, String city, String state, String street, long dateOfBirth) {
        Log.d(TAG, "checkEntries: entries Checked and validating...");
        Address address = new Address(street, city, state);
        Log.d(TAG, "checkEntries: Address Object Created :" + address.toString());
        final User user = new User(firstName, lastName, email, phoneNumber, address, password, gender, dateOfBirth);
        Log.d(TAG, "checkEntries: User Object Created: " + user.toString());

        Log.d(TAG, "Sign Up URL Created: " + services.createAccount(user).request().url());

        services.createAccount(user).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                // TODO: 2019-02-15 Check For Response Object to match the equivalent response on the server side
                Log.d(TAG, "starting request for sign up");
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body().getUser().getEmail());
                    if (response.body().getMessage().equals("Mail exists")) {
                        Log.d(TAG, "onResponse: Account Already exist");
                        Toast.makeText(SignUpActivity.this, "This mail is already exist", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d(TAG, "onResponse: Account Created");
                        Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d(TAG, "onResponse: Failed");
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                gender = getString(R.string.male);
                break;
            default:
                gender = getString(R.string.female);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
