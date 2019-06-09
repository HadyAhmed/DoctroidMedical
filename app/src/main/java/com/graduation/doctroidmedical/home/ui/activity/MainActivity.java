package com.graduation.doctroidmedical.home.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(activityMainBinding.navigation, navController);

        activityMainBinding.navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int i = menuItem.getItemId();
        if (i == R.id.navigation_medicine_news) {
            navController.navigate(R.id.newsFragment);
            return true;
        } else if (i == R.id.navigation_medicine_result) {
            navController.navigate(R.id.hospitalFragment);
            return true;
        } else if (i == R.id.navigation_chat) {
            navController.navigate(R.id.appointmentAndComplainsFragment);
            return true;
        } else if (i == R.id.navigation_user) {
            navController.navigate(R.id.profileFragment);
            return true;
        }
        return false;
    }


//    @Override
//    public void onBackPressed() {
//        if (getSharedPreferences(SignInActivity.USER_SHARED_PREF, MODE_PRIVATE).getBoolean(SignInActivity.REMEMBER_ME, false)) {
//            finishAffinity();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
