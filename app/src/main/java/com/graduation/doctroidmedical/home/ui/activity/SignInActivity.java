package com.graduation.doctroidmedical.home.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.databinding.ActivitySignInBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.loginresponse.LoginModel;
import com.graduation.doctroidmedical.home.pojo.loginresponse.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity
        implements View.OnClickListener {
    // Constants for shared preferences
    public static final String USER_SHARED_PREF = "user_pref_key";
    public static final String USER_TOKEN = "username_key";
    public static final String USER_ID = "uid_pref";
    public static final String REMEMBER_ME = "remember_me_key";
    private static final String TAG = SignInActivity.class.getSimpleName();
    private static final String IS_SIGNED_IN = "is_sign_in_pref";
    private ActivitySignInBinding signInBinding;
    private WebServices services = WebServices.startService.create(WebServices.class);

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferenceManager.getDefaultSharedPreferences(this).contains(IS_SIGNED_IN)) {
            if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(IS_SIGNED_IN, false)) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
    }

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
        signInBinding.createAccountTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        // Login in button listener
        if (viewId == R.id.login_btn) {
            if (signInBinding.usernameEt.getText().toString().equals("") || signInBinding.passwordEt.getText().toString().equals("")) {
                Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                loginWithCredentials(new LoginModel(signInBinding.usernameEt.getText().toString(), signInBinding.passwordEt.getText().toString()));
            }
        } else if (viewId == R.id.create_account_tv) {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    private void loginWithCredentials(LoginModel loginModel) {
        signInBinding.loginProgressBar.setVisibility(View.VISIBLE);
        services.checkEntryValidation(loginModel)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                        if (response.body() != null) {
                            if (response.body().isSuccess()) {
                                Toast.makeText(SignInActivity.this, "successfully logged in", Toast.LENGTH_SHORT).show();
                                saveUser(response.body());
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignInActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                        Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUser(LoginResponse body) {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(USER_ID, body.getUser().getId())
                .putBoolean(IS_SIGNED_IN, true)
                .putString(USER_TOKEN, body.getToken())
                .apply();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
