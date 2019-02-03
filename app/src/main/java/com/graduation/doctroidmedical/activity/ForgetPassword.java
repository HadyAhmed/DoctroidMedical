package com.graduation.doctroidmedical.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.graduation.doctroidmedical.R;

public class ForgetPassword extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Toolbar toolbar = findViewById(R.id.forget_password_toolbar);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
