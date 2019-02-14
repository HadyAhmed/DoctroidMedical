package com.graduation.doctroidmedical.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.adapter.SliderAdapter;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SplashActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener,
        View.OnClickListener {

    private LinearLayout dotLayout;
    private ViewPager viewPager;
    private MaterialButton nextBtn, backBtn, gettingStarted;
    // Array of string will hold the dots indicators
    private TextView[] mDots;
    // Detect current viewed page of the view pager
    private int currentPagePosition;

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
        viewPager.setAdapter(new SliderAdapter(this));
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
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
