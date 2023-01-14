package com.example.krankenhaus.ui.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.krankenhaus.R;
import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.repository.BedRepository;
import com.example.krankenhaus.srccode.repository.PatientRepository;
import com.example.krankenhaus.srccode.repository.VisitRepository;
import com.example.krankenhaus.ui.doctor.ui.main.DoctorViewModel;
import com.example.krankenhaus.ui.doctor.ui.main.PatientInfoFragment;
import com.example.krankenhaus.ui.doctor.ui.main.PatientListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.krankenhaus.databinding.ActivityDoctorBinding;

import java.time.LocalDate;

public class DoctorActivity extends AppCompatActivity {
    private ActivityDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navigationView = findViewById(R.id.doctor_navigation_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.doctor_navigation_home, R.id.doctor_navigation_dashboard, R.id.doctor_navigation_notifications).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_doctor);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.doctorNavigationView, navController);

        getSupportActionBar().setTitle("Doctor");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        obtainViewModel(this);
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    public static DoctorViewModel obtainViewModel(FragmentActivity activity) {
        PatientRepository patientRepository = PatientRepository.getInstance(activity.getApplication());
        BedRepository bedRepository = BedRepository.getInstance(activity.getApplication());
        VisitRepository visitRepository = VisitRepository.getInstance(activity.getApplication());
        DoctorViewModel doctorViewModel = new ViewModelProvider(activity).get(DoctorViewModel.class);
        doctorViewModel.setRepository(patientRepository, visitRepository);

        patientRepository.insertPatient(new Patient("123", 2, "Doan", LocalDate.of(2001, 10, 02), "Hanoi", "Hanoi", "123"));

        return doctorViewModel;
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