package com.example.krankenhaus.ui.service;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.krankenhaus.databinding.ActivityServiceBinding;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

public class ServiceActivity extends AppCompatActivity {
    private ActivityServiceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.laborButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLabor();
            }
        });

        getSupportActionBar().setTitle("Service");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openActivityLabor() {
        Intent intent = new Intent(this, LaborActivity.class);
        startActivity(intent);
    }
}