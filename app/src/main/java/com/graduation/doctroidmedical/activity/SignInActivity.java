package com.graduation.doctroidmedical.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.databinding.ActivitySignInBinding;
import com.graduation.doctroidmedical.pojo.LoginModel;
import com.graduation.doctroidmedical.pojo.LoginResponse;
import com.graduation.doctroidmedical.utils.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = SignInActivity.class.getSimpleName();
    // Constants for shared preferences
    private static final String USER_SHARED_PREF = "user_pref_key";
    private static final String USER_USERNAME = "username_key";
    private static final String REMEMBER_ME = "remember_me_key";
    private ActivitySignInBinding signInBinding;
    private WebServices services = WebServices.startService.create(WebServices.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        // Set up custom text for Create Account Text View
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            signInBinding.createAccountTv.setText(Html.fromHtml(getString(R.string.create_account), Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
        } else {
            signInBinding.createAccountTv.setText(Html.fromHtml(getString(R.string.create_account)), TextView.BufferType.SPANNABLE);
        }

        // Trigger the action for the buttons
        signInBinding.loginBtn.setOnClickListener(this);
        signInBinding.forgetPasswordTv.setOnClickListener(this);
        signInBinding.createAccountTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        // Login in button listener
        if (viewId == R.id.login_btn) {
            validInfo();
        } else if (viewId == R.id.forget_password_tv) {
            startActivity(new Intent(this, ForgetPassword.class));
        } else if (viewId == R.id.create_account_tv) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    private void saveUserLocalInfo(String username, boolean rememberChecked) {
        SharedPreferences.Editor userLocalInfo =
                getSharedPreferences(USER_SHARED_PREF, MODE_PRIVATE).edit();
        userLocalInfo.putString(USER_USERNAME, username);
        userLocalInfo.putBoolean(REMEMBER_ME, rememberChecked);
        userLocalInfo.apply();
    }

    /**
     * this method will check for validation and set error messages for each of username and password
     */
    private void validInfo() {
        Log.d(TAG, "validInfo: Login button triggered");
        // if valid response return true else return false
        if (isUsernameValid(signInBinding.usernameEt)
                && isPasswordValid(signInBinding.passwordEt)) {
            Log.d(TAG, "validInfo: Valid Entry Format, Checking for validation...");
            // Getting user name from view
            String username = signInBinding.usernameEt.getText().toString();
            // Getting password name from view
            String password = signInBinding.passwordEt.getText().toString();
            // Creating instance from LoginModel blueprint
            final LoginModel loginModel = new LoginModel(username, password);
            Log.d(TAG, "onCreate: URL Generated: " + services.checkEntryValidation(loginModel).request().url());
            services.checkEntryValidation(loginModel).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    if (response.body() != null) {
                        if (response.body().isSuccess()) {
                            Log.d(TAG, "onResponse: Successful login");
                            // TODO: 2019-02-15 Check for remember me checked, if so save user info to shared preference
                            // to prevent the user from repeat login cycle
                            Toast.makeText(SignInActivity.this, "Hello, " + loginModel.getEmail(), Toast.LENGTH_SHORT).show();
                            Intent startApplication = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(startApplication);
                        }
                    } else {
                        Toast.makeText(SignInActivity.this, "This Account is invalid or not exist", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: Empty Body");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {
            if (!isUsernameValid(signInBinding.usernameEt)) {
                Log.d(TAG, "validInfo: empty or invalid username");
                signInBinding.usernameEt.setError("Insert Your Mail");
            } else if (!isPasswordValid(signInBinding.passwordEt)) {
                Log.d(TAG, "validInfo: empty or invalid password");
                signInBinding.passwordEt.setError("Insert Password");
            }
        }
    }

    /**
     * @return true if the password is valid and false if not
     */
    private boolean isPasswordValid(EditText password) {
        return !TextUtils.isEmpty(password.getText().toString());
    }

    private boolean isUsernameValid(EditText username) {
        String usernameFormat = username.getText().toString();
        return !TextUtils.isEmpty(usernameFormat) && usernameFormat.endsWith(".com");
    }

}
