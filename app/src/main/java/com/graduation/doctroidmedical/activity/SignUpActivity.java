package com.graduation.doctroidmedical.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.graduation.doctroidmedical.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.sign_up_toolbar);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
