package com.example.krankenhaus.ui.doctor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.krankenhaus.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.krankenhaus.databinding.ActivityDoctorBinding;

public class DoctorActivity extends AppCompatActivity {

    private ActivityDoctorBinding binding;
    DoctorDashboardViewModel doctorDashboardViewModel = new ViewModelProvider(this).get(DoctorDashboardViewModel.class);
    DoctorNotificationsViewModel doctorNotificationsViewModel = new ViewModelProvider(this).get(DoctorNotificationsViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navigationView = findViewById(R.id.doctor_navigation_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.doctor_navigation_home, R.id.doctor_navigation_dashboard, R.id.doctor_navigation_notifications).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_doctor);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.doctorNavigationView, navController);

        getSupportActionBar().setTitle("Doctor");
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}