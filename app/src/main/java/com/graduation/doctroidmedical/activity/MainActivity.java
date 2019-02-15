package com.graduation.doctroidmedical.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.graduation.doctroidmedical.R;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_medicine_news:
                Toast.makeText(MainActivity.this, "news", Toast.LENGTH_SHORT).show();
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
}
