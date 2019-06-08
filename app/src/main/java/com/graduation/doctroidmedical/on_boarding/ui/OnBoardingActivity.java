package com.graduation.doctroidmedical.on_boarding.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.home.ui.activity.MainActivity;
import com.graduation.doctroidmedical.home.ui.activity.SignInActivity;
import com.graduation.doctroidmedical.on_boarding.adapter.OnBoardAdapter;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class OnBoardingActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener,
        View.OnClickListener {

    private static final String SPLASH_PREF = "splash_pref";
    private static final String FIST_TIME = "first_time_key";
    private LinearLayout dotLayout;
    private ViewPager viewPager;
    private MaterialButton nextBtn, backBtn, gettingStarted;
    // Array of string will hold the dots indicators
    private TextView[] mDots;
    // Detect current viewed page of the view pager
    private int currentPagePosition;

    @Override
    protected void onStart() {
        super.onStart();
        // Creating reference for splash screen activity

        if (getSharedPreferences(SPLASH_PREF, MODE_PRIVATE).contains(FIST_TIME)) {
            // check if it's the first time to launch or not
            if (getSharedPreferences(SPLASH_PREF, MODE_PRIVATE).getBoolean(FIST_TIME, false)) {
                if (getSharedPreferences(SignInActivity.USER_SHARED_PREF, MODE_PRIVATE).contains(SignInActivity.REMEMBER_ME)) {
                    if (getSharedPreferences(SignInActivity.USER_SHARED_PREF, MODE_PRIVATE).getBoolean(SignInActivity.REMEMBER_ME, false)) {
                        finish();
                        startActivity(new Intent(this, MainActivity.class));
                    }
                }
                finish();
                startActivity(new Intent(this, SignInActivity.class));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Setup the views
        viewPager = findViewById(R.id.view_pager);
        dotLayout = findViewById(R.id.dots_indicator);
        nextBtn = findViewById(R.id.next_button);
        backBtn = findViewById(R.id.back_button);
        gettingStarted = findViewById(R.id.getting_started_btn);

        // Setup for the view pager
        viewPager.setAdapter(new OnBoardAdapter(this));
        viewPager.addOnPageChangeListener(this);
        nextBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        gettingStarted.setOnClickListener(this);

        addDots(0);
    }

    private void addDots(int position) {
        // this will hold the dots indicators as a text from html
        mDots = new TextView[3];
        dotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(48);
            mDots[i].setTextColor(getResources().getColor(android.R.color.darker_gray));
            dotLayout.addView(mDots[i]);
        }
        mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        addDots(i);
        currentPagePosition = i;
        if (i == 0) {
            // Logic First Page
            gettingStarted.setVisibility(INVISIBLE);
            nextBtn.setVisibility(VISIBLE);
            backBtn.setVisibility(INVISIBLE);
        } else if (i == mDots.length - 1) {
            // Logic Last Page
            gettingStarted.setVisibility(VISIBLE);
            nextBtn.setVisibility(INVISIBLE);
            backBtn.setVisibility(INVISIBLE);
        } else {
            // Logic Second Page
            gettingStarted.setVisibility(INVISIBLE);
            nextBtn.setVisibility(VISIBLE);
            backBtn.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.next_button) {
            // Set current page position for the view pager to currentPagePosition Variable
            viewPager.setCurrentItem(currentPagePosition + 1);
        } else if (viewId == R.id.back_button) {
            // Set current page position for the view pager to currentPagePosition Variable
            viewPager.setCurrentItem(currentPagePosition - 1);
        } else if (viewId == R.id.getting_started_btn) {
            // Start the login activity
            SharedPreferences.Editor splashPref = getSharedPreferences(SPLASH_PREF, MODE_PRIVATE).edit();
            splashPref.putBoolean(FIST_TIME, true)
                    .apply();
            startActivity(new Intent(this, SignInActivity.class));
        }
    }
}
