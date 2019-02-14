package com.graduation.doctroidmedical.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.graduation.doctroidmedical.R;

public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener {
    // Constants for shared preferences
    private static final String USER_SHARED_PREF = "user_pref_key";
    private static final String USER_USERNAME = "username_key";
    private static final String REMEMBER_ME = "remember_me_key";

    private TextInputLayout usernameLayout, passwordLayout;
    private TextInputEditText usernameEt, passwordEt;
    private CheckBox rememberMe;
    private TextView forgetPassword, createAccount;
    private MaterialButton loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Find All the view references
        usernameLayout = findViewById(R.id.username_layout);
        passwordLayout = findViewById(R.id.name_layout);
        usernameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.passowrd_et);
        rememberMe = findViewById(R.id.remember_me);
        forgetPassword = findViewById(R.id.forget_password_tv);
        createAccount = findViewById(R.id.create_account_tv);
        loginBtn = findViewById(R.id.login_btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            createAccount.setText(Html.fromHtml(getString(R.string.create_account), Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
        } else {
            createAccount.setText(Html.fromHtml(getString(R.string.create_account)), TextView.BufferType.SPANNABLE);
        }

        loginBtn.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
        createAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        // Login in button listener
        if (viewId == R.id.login_btn) {
            if (validInfo()) {
                // Saving regular info in the shared preferences
                String username = usernameEt.getText().toString();
                boolean rememberChecked = rememberMe.isChecked();
                saveUserLocalInfo(username, rememberChecked);
                // Pass any data with the intent if needed
                startActivity(new Intent(this, MainActivity.class));
            }
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
    private boolean validInfo() {
        // TODO: 2019-02-03 Add your login logic here then start next activity if successfully logged in.
        // if valid response return true else return false
        if (isUsernameValid() && isPasswordValid()) {

            return true;
        } else {
            if (!isUsernameValid()) {
                usernameEt.setError("Add Error Message Here");
            }
            if (!isPasswordValid()) {
                passwordEt.setError("Add Error Message Here");
            }
            return false;
        }
    }

    /**
     * @return true if the password is valid and false if not
     */
    private boolean isPasswordValid() {
        // Placeholder for developing
        return true;
    }

    private boolean isUsernameValid() {
        // Placeholder for developing
        return true;
    }

    @Override
    public void onBackPressed() {
        //uncomment if action not needed
        finishAffinity();
    }

}
