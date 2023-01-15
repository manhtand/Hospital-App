package com.example.krankenhaus.ui.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.ui.administrator.AdministratorActivity;
import com.example.krankenhaus.ui.doctor.DoctorActivity;
import com.example.krankenhaus.ui.menubar.MainActivity;

import android.view.View;
import android.widget.Button;

import com.example.krankenhaus.R;
import com.example.krankenhaus.databinding.ActivityLogInScreenBinding;
import com.example.krankenhaus.ui.service.ServiceActivity;

public class LogInScreenActivity extends AppCompatActivity {

    private ActivityLogInScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        binding = ActivityLogInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnDoctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivityDoctor();
            }
        });

        binding.btnAdministrator.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {openActivityAdministrator();}
        });

        binding.btnService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { openActivityService();
            }
        });
        
        BedRepository bedRepository = BedRepository.getInstance(this.getApplication());
        for (int i = 1; i <= 21; i++) {
            bedRepository.insertBed(new Bed(i));
        }
    }

    public void openActivityDoctor(){
        Intent intent = new Intent(this, DoctorActivity.class);
        startActivity(intent);
    }
    public void openActivityAdministrator(){
        Intent intent = new Intent(this, AdministratorActivity.class);
        startActivity(intent);
    }
    public void openActivityService(){
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }
}