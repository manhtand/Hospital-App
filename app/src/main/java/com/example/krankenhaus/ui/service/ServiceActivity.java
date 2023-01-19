package com.example.krankenhaus.ui.service;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.krankenhaus.databinding.ActivityServiceBinding;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.ui.service.labor.LaborActivity;

import java.util.List;

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

        PatientRepository patientRepository = PatientRepository.getInstance(this.getApplication());
        patientRepository.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patients.size();
            }
        });
    }

    public void openActivityLabor() {
        Intent intent = new Intent(this, LaborActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        else {
            super.onBackPressed();
        }
    }
}