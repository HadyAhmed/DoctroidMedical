package com.graduation.doctroidmedical.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.databinding.ActivityMainBinding;
import com.graduation.doctroidmedical.ui.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if (savedInstanceState == null) {
            replaceFragment(new NewsFragment());
        }
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_medicine_news:
                replaceFragment(new NewsFragment());
                return true;
            case R.id.navigation_medicine_result:
                Toast.makeText(MainActivity.this, "result", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_chat:
                Toast.makeText(MainActivity.this, "chat", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.navigation_user:
                Toast.makeText(MainActivity.this, "user", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    private void replaceFragment(NewsFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSharedPreferences(SignInActivity.USER_SHARED_PREF, MODE_PRIVATE).getBoolean(SignInActivity.REMEMBER_ME, false)) {
            finishAffinity();
        } else {
            super.onBackPressed();
        }
    }
}
